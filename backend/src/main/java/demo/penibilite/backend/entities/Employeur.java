package demo.penibilite.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "employeurs")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employeur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; 
	
	@Column(nullable = false)
    private String raisonSociale;

    @Column(nullable = false, unique = true)
    private String siret;
	
	@Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    private String adresse;

    @OneToMany(mappedBy = "employeur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exposition> expositions;
}
