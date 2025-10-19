package demo.penibilite.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "salaries")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Salarie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(nullable = false, unique = true)
    private String username; // login unique pour lier le salarié à son identité dans keyloak

    @Column(nullable = false)
    private String nom;

    @Column(nullable = false)
    private String prenom;

    @Column(nullable = false, unique = true)
    private String numeroSecu; // identifiant unique
	
	@Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    private LocalDate dateNaissance;

    @OneToMany(mappedBy = "salarie", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exposition> expositions;
}
