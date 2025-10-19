package demo.penibilite.backend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Table(name = "facteurs_penibilite")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacteurPenibilite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String code; // ex: FP01, FP02

    @Column(nullable = false)
    private String libelle;

    @Column(length = 250)
    private String description;
    
    @Column(nullable = false)
    private double coefficient;

    @OneToMany(mappedBy = "facteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Exposition> expositions;
}
