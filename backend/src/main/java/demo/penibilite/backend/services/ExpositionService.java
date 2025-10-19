package demo.penibilite.backend.services;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.*;
import demo.penibilite.backend.dto.ExpositionDTO;
import demo.penibilite.backend.entities.*;
import demo.penibilite.backend.exception.ResourceNotFoundException;
import demo.penibilite.backend.mappers.ExpositionMapper;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Transactional
public class ExpositionService implements IExpositionService{

    private final ExpositionRepository expositionRepository;
    private final SalarieRepository salarieRepository;
    private final EmployeurRepository employeurRepository;
    private final FacteurPenibiliteRepository facteurRepository;
    private final AgentRepository agentRepository;
    private final ExpositionMapper expositionMapper;

    public ExpositionService(ExpositionRepository expositionRepository,
                             SalarieRepository salarieRepository,
                             EmployeurRepository employeurRepository,
                             FacteurPenibiliteRepository facteurRepository,
                             AgentRepository agentRepository,
                             ExpositionMapper expositionMapper) {
        this.expositionRepository = expositionRepository;
        this.salarieRepository = salarieRepository;
        this.employeurRepository = employeurRepository;
        this.facteurRepository = facteurRepository;
        this.agentRepository = agentRepository;
        this.expositionMapper = expositionMapper;
    }

    // Employeur déclare une exposition
    @Override
    public ResponseEntity<ExpositionDTO> declarerExposition(Long employeurId, String salarieNumSecu, Long facteurId, String periode, Integer jourPenibles) {
        Salarie salarie = salarieRepository.findByNumeroSecu(salarieNumSecu)
                .orElseThrow(() -> new ResourceNotFoundException("Salarié introuvale pour le num-securite-sociale = " + salarieNumSecu));
        Employeur employeur = employeurRepository.findById(employeurId)
                .orElseThrow(() -> new ResourceNotFoundException("Employeur introuvable pour l'Id = " + salarieNumSecu));
        FacteurPenibilite facteur = facteurRepository.findById(facteurId)
                .orElseThrow(() -> new ResourceNotFoundException("Facteur de penibilité introuvable pour l'Id = " + facteurId));

        Exposition exposition = Exposition.builder()
                .salarie(salarie)
                .employeur(employeur)
                .dateDeclaration(LocalDateTime.now())
                .facteur(facteur)
                .periode(periode)
                .jourPenibles(jourPenibles)
                .status(ExpositionStatus.PENDING)
                .build();

        Exposition saved = expositionRepository.save(exposition);
        return ResponseEntity.ok(expositionMapper.toDto(saved));
    }

    // Agent valide ou rejette une exposition
    @Override
    public ExpositionDTO validerExposition(Long expositionId, Long agentId, boolean valide, String commentaire) {
        Exposition exposition = expositionRepository.findById(expositionId)
                .orElseThrow(() -> new RuntimeException("Exposition introuvable"));
        Agent agent = agentRepository.findById(agentId)
                .orElseThrow(() -> new RuntimeException("Agent introuvable"));

        exposition.setValidePar(agent);
        exposition.setDateValidation(LocalDateTime.now());
        exposition.setCommentaireAgent(commentaire);
        exposition.setStatus(valide ? ExpositionStatus.VALIDATED : ExpositionStatus.REJECTED);

        Exposition saved = expositionRepository.save(exposition);
        return expositionMapper.toDto(saved);
    }

    // Salarié consulte ses expositions
    @Override
    public List<ExpositionDTO> consulterExpositions(Long salarieId) {
        List<Exposition> list = expositionRepository.findBySalarieId(salarieId);
        return expositionMapper.toDtoList(list);
    }

    // Agent suit les expositions en attente
    @Override
    public List<ExpositionDTO> listerExpositionsEnAttente() {
        List<Exposition> list = expositionRepository.findByStatus(ExpositionStatus.PENDING);
        return expositionMapper.toDtoList(list);
    }
}

