package demo.penibilite.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.FacteurPenibiliteRepository;
import demo.penibilite.backend.dto.FacteurPenibiliteDTO;
import demo.penibilite.backend.entities.FacteurPenibilite;
import demo.penibilite.backend.mappers.FacteurPenibiliteMapper;

import java.util.List;



@Service
@Transactional
public class FacteurPenibiliteService implements IFacteurPenibiliteService {

    private final FacteurPenibiliteRepository facteurRepository;
    private final FacteurPenibiliteMapper facteurMapper;

    public FacteurPenibiliteService(FacteurPenibiliteRepository facteurRepository, FacteurPenibiliteMapper facteurMapper) {
        this.facteurRepository = facteurRepository;
        this.facteurMapper = facteurMapper;
    }

    @Override
    public FacteurPenibiliteDTO creerFacteur(FacteurPenibilite facteur) {
        FacteurPenibilite saved = facteurRepository.save(facteur);
        return facteurMapper.toDto(saved);
    }

    @Override
    public FacteurPenibiliteDTO getByCode(String code) {
        FacteurPenibilite facteur = facteurRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Facteur introuvable"));
        return facteurMapper.toDto(facteur);
    }

    @Override
    public List<FacteurPenibiliteDTO> getAll() {
        return facteurMapper.toDtoList(facteurRepository.findAll());
    }
}



