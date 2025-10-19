package demo.penibilite.backend.security;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthConverter implements Converter<Jwt, AbstractAuthenticationToken> {
	private final JwtGrantedAuthoritiesConverter jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

	@Override
	public AbstractAuthenticationToken convert(Jwt jwt) {
		Collection<GrantedAuthority> authorities = Stream
				.concat(jwtGrantedAuthoritiesConverter.convert(jwt).stream(), extractResourceRoles(jwt).stream())
				.collect(Collectors.toSet());
		return new JwtAuthenticationToken(jwt, authorities, jwt.getClaim("preferred_username"));
	}

	private Collection<GrantedAuthority> extractResourceRoles(Jwt jwt) {
	    Map<String, Object> realmAccess;
	    Collection<String> roles;
	    if (jwt.getClaim("realm_access") == null) {
	        return Set.of();
	    }
	    realmAccess = jwt.getClaim("realm_access");
	    Object rolesObject = realmAccess.get("roles");
	    if (rolesObject instanceof Collection<?>) {
	        roles = ((Collection<?>) rolesObject).stream()
	                .filter(role -> role instanceof String)
	                .map(role -> (String) role)
	                .collect(Collectors.toSet());
	    } else {
	        return Set.of();
	    }
	    return roles.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toSet());
	}


}



