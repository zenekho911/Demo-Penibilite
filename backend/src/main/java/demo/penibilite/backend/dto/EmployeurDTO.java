package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EmployeurDTO {
    private Long id;
    private String raisonSociale;
    private String siret;
    private String adresse;
}
