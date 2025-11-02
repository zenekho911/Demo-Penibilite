package demo.penibilite.backend.web;

import org.springframework.web.bind.annotation.*;

import demo.penibilite.backend.dto.AgentDTO;
import demo.penibilite.backend.dto.ExpositionDTO;
import demo.penibilite.backend.dto.ValidationExpositionRequest;
import demo.penibilite.backend.entities.Agent;
import demo.penibilite.backend.services.IAgentService;
import demo.penibilite.backend.services.IExpositionService;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
public class AgentController {

    private final IAgentService agentService;
    private final IExpositionService expositionService;
    

    public AgentController(IAgentService agentService, IExpositionService expositionService) {
        this.agentService = agentService;
        this.expositionService = expositionService;
       
    }

    @PostMapping
    public AgentDTO creerAgent(@RequestBody Agent agent) {
        return agentService.creerAgent(agent);
    }

    @GetMapping("/{email}")
    public AgentDTO getByEmail(@PathVariable String email) {
        return agentService.getByEmail(email);
    }

    @GetMapping("/expositions/attente")
    public List<ExpositionDTO> listerExpositionsEnAttente() {
        return expositionService.listerExpositionsEnAttente();
    }

    @PostMapping("/valider-exposition/{expositionId}")
    public ExpositionDTO validerExposition(@PathVariable Long expositionId,
    		@RequestBody ValidationExpositionRequest request) {
        return expositionService.validerExposition(expositionId, request.getAgentId(), request.isValide(), request.getCommentaire());
    }
        
    @GetMapping("/update-salaries-points")
    public String batchToUpdateSalariesPoints() {
        return this.agentService.updateSalariesPointsBatch();    
    }
    
    
}

