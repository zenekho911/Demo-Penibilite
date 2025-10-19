package demo.penibilite.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DeclarationExpositionRequest {
	
	String salarieNumSecu;
	Long facteurId;
	String periode;
	Integer jourPenibles;
	
	 

}
