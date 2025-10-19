package demo.penibilite.backend.web;

import org.springframework.web.bind.annotation.*;

import demo.penibilite.backend.dto.ExpositionDTO;
import demo.penibilite.backend.dto.SalarieDTO;
import demo.penibilite.backend.entities.Salarie;
import demo.penibilite.backend.services.IExpositionService;
import demo.penibilite.backend.services.ISalarieService;

import java.util.List;

@RestController
@RequestMapping("/api/salaries")
public class SalarieController {

    private final ISalarieService salarieService;
    private final IExpositionService expositionService;

    public SalarieController(ISalarieService salarieService, IExpositionService expositionService) {
        this.salarieService = salarieService;
        this.expositionService = expositionService;
    }

    @PostMapping
    public SalarieDTO creerSalarie(@RequestBody Salarie salarie) {
        return salarieService.creerSalarie(salarie);
    }

    @GetMapping("/secu/{numeroSecu}")
    public SalarieDTO getByNumeroSecu(@PathVariable String numeroSecu) {
        return salarieService.getByNumeroSecu(numeroSecu);
    }
    
    @GetMapping("/email/{email}")
    public SalarieDTO getByEmail(@PathVariable String email) {
        return salarieService.getByEmail(email);
    }

    @GetMapping("/{salarieId}/expositions")
    public List<ExpositionDTO> consulterExpositions(@PathVariable Long salarieId) {
        return expositionService.consulterExpositions(salarieId);
    }
}


