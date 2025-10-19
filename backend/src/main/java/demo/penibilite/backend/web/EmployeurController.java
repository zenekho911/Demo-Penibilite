package demo.penibilite.backend.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import demo.penibilite.backend.dto.DeclarationExpositionRequest;
import demo.penibilite.backend.dto.EmployeurDTO;
import demo.penibilite.backend.dto.ExpositionDTO;
import demo.penibilite.backend.entities.Employeur;
import demo.penibilite.backend.services.IEmployeurService;
import demo.penibilite.backend.services.IExpositionService;




@RestController
@RequestMapping("/api/employeurs")
public class EmployeurController {

    private final IEmployeurService employeurService;
    private final IExpositionService expositionService;

    public EmployeurController(IEmployeurService employeurService, IExpositionService expositionService) {
        this.employeurService = employeurService;
        this.expositionService = expositionService;
    }

    @PostMapping
    public EmployeurDTO creerEmployeur(@RequestBody Employeur employeur) {
        return employeurService.creerEmployeur(employeur);
    }

    @GetMapping("/email/{email}")
    public EmployeurDTO getByEmail(@PathVariable String email) {
        return employeurService.getByEmail(email);
    }
    
    @GetMapping("/siret/{siret}")
    public EmployeurDTO getBySiret(@PathVariable String siret) {
        return employeurService.getBySiret(siret);
    }

    @PostMapping("/declarer-exposition/{employeurId}")
    public ResponseEntity<ExpositionDTO> declarerExposition(@PathVariable Long employeurId, @RequestBody DeclarationExpositionRequest request) {
        return expositionService.declarerExposition(employeurId,
        		request.getSalarieNumSecu(), request.getFacteurId(), request.getPeriode(), request.getJourPenibles());
    }
}


