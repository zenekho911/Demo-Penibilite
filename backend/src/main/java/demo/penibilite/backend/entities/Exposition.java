package demo.penibilite.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "expositions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Exposition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String periode;
    
    private Integer jourPenibles;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExpositionStatus status;// = ExpositionStatus.PENDING;
    
    private LocalDateTime dateDeclaration;

    private LocalDateTime dateValidation;

    @Column(length = 250)
    private String commentaireAgent;

    // Relations clés
    @ManyToOne
    @JoinColumn(name = "salarie_id", nullable = false)
    private Salarie salarie;

    @ManyToOne
    @JoinColumn(name = "employeur_id", nullable = false)
    private Employeur employeur;

    @ManyToOne
    @JoinColumn(name = "facteur_id", nullable = false)
    private FacteurPenibilite facteur;

    @ManyToOne
    @JoinColumn(name = "agent_id")
    private Agent validePar;
}


