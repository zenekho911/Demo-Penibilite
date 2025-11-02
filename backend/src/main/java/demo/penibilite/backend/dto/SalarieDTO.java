package demo.penibilite.backend.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SalarieDTO {
    private Long id;
    private String nom;
    private String prenom;
    private String numeroSecu; // optionnel à exposer selon besoin
    private double totalPoints;
    private LocalDateTime dateLastBatchUpdate;
}
