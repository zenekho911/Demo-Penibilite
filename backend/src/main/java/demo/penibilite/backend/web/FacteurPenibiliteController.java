package demo.penibilite.backend.web;

import org.springframework.web.bind.annotation.*;

import demo.penibilite.backend.dto.FacteurPenibiliteDTO;
import demo.penibilite.backend.entities.FacteurPenibilite;
import demo.penibilite.backend.services.IFacteurPenibiliteService;

import java.util.List;

@RestController
@RequestMapping("/api/facteurs")
public class FacteurPenibiliteController {

    private final IFacteurPenibiliteService facteurService;

    public FacteurPenibiliteController(IFacteurPenibiliteService facteurService) {
        this.facteurService = facteurService;
    }

    @PostMapping
    public FacteurPenibiliteDTO creerFacteur(@RequestBody FacteurPenibilite facteur) {
        return facteurService.creerFacteur(facteur);
    }

    @GetMapping
    public List<FacteurPenibiliteDTO> getAll() {
        return facteurService.getAll();
    }
}
