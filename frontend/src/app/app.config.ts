import { APP_INITIALIZER, ApplicationConfig, Provider } from '@angular/core';
import { provideRouter } from '@angular/router';
import { routes } from './app.routes';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { KeycloakService } from 'keycloak-angular';

import { HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { tokenInterceptor } from './core/interceptors/token.interceptor';






function initializeKeycloak(keycloak: KeycloakService) {
  return () =>
    keycloak.init({
      config: {
        url: "http://keycloak-penibilite:8080",
        realm: 'demo-penibilite',
        clientId: 'frontend-penibilite-client'
      },
      initOptions: {
        onLoad: "login-required",  // affichage login direct
        checkLoginIframe: false
      }
    });
}

// Provider for Keycloak Initialization
const KeycloakInitializerProvider: Provider = {
  provide: APP_INITIALIZER,
  useFactory: initializeKeycloak,
  multi: true,
  deps: [KeycloakService]
}


// Provider for Keycloak Interceptor 
const KeycloackHttpInterceptorProvider: Provider = {
  provide: HTTP_INTERCEPTORS, 
  useClass: tokenInterceptor, 
  multi: true
}





export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptorsFromDi()), // Provides HttpClient with interceptors
    provideAnimationsAsync(),
    KeycloakInitializerProvider, // Initializes Keycloak
    KeycloackHttpInterceptorProvider,   // Provides  Keycloak Bearer Interceptor
    KeycloakService, // Service for Keycloak
    provideRouter(routes), provideAnimationsAsync() // Provides routing for the application

  ]
};
