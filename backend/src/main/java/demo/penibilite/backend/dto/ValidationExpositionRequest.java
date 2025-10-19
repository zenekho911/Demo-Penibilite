package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidationExpositionRequest {
	
	private Long agentId;
    private boolean valide;
    private String commentaire;

}
