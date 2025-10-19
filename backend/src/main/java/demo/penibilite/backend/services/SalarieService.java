package demo.penibilite.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.SalarieRepository;
import demo.penibilite.backend.dto.SalarieDTO;
import demo.penibilite.backend.entities.Salarie;
import demo.penibilite.backend.mappers.SalarieMapper;

import java.util.List;

@Service
@Transactional
public class SalarieService implements ISalarieService {

    private final SalarieRepository salarieRepository;
    private final SalarieMapper salarieMapper;

    public SalarieService(SalarieRepository salarieRepository, SalarieMapper salarieMapper) {
        this.salarieRepository = salarieRepository;
        this.salarieMapper = salarieMapper;
    }

    @Override
    public SalarieDTO creerSalarie(Salarie salarie) {
        Salarie saved = salarieRepository.save(salarie);
        return salarieMapper.toDto(saved);
    }
    
    @Override
    public SalarieDTO getByEmail(String email) {
		Salarie salarie = salarieRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Salarié introuvable"));
        return salarieMapper.toDto(salarie);
	}

    @Override
    public SalarieDTO getByNumeroSecu(String numeroSecu) {
        Salarie salarie = salarieRepository.findByNumeroSecu(numeroSecu)
                .orElseThrow(() -> new RuntimeException("Salarié introuvable"));
        return salarieMapper.toDto(salarie);
    }

    @Override
    public List<SalarieDTO> getAll() {
        return salarieMapper.toDtoList(salarieRepository.findAll());
    }

	
}



