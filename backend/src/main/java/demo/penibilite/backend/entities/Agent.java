package demo.penibilite.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "agents")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username; 

    @Column(nullable = false)
    private String nomComplet;

    @Column(nullable = false, unique = true)
    private String email;

    private String telephone;

    @Column(nullable = false)
    private boolean actif;// = true;


    private LocalDateTime dateCreation;
    private LocalDateTime dateMiseAJour;

    @OneToMany(mappedBy = "validePar", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Exposition> expositionsValidees;
}


