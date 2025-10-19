package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpositionDTO {
    private Long id;
    private String periode;
    private Integer jourPenibles;
    private String status;
    private String commentaireAgent;
    private LocalDateTime dateDeclaration;
    private LocalDateTime dateValidation;
    private SalarieDTO salarie;
    private EmployeurDTO employeur;
    private FacteurPenibiliteDTO facteur;
    private AgentDTO validePar; // null si pas encore validé
}
