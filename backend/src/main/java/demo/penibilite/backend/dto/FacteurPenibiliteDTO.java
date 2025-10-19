package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FacteurPenibiliteDTO {
    private Long id;
    private String code;
    private String libelle;
    private String description;
    private double coefficient;
}
