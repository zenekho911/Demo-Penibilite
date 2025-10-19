package demo.penibilite.backend.services;


import java.util.List;

import org.springframework.http.ResponseEntity;

import demo.penibilite.backend.dto.ExpositionDTO;



public interface IExpositionService {
	
	// Salarié consulte ses expositions
    public List<ExpositionDTO> consulterExpositions(Long salarieId);
	
	// Employeur déclare une exposition
    public ResponseEntity<ExpositionDTO> declarerExposition(Long employeurId, String salarieNumSecu, Long facteurId, String periode, Integer jourPenibles);

    // Agent valide ou rejette une exposition
    public ExpositionDTO validerExposition(Long expositionId, Long agentId, boolean valide, String commentaire);

    // Agent suit les expositions en attente
    public List<ExpositionDTO> listerExpositionsEnAttente();

}
