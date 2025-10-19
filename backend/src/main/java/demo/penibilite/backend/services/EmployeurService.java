package demo.penibilite.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.EmployeurRepository;
import demo.penibilite.backend.dto.EmployeurDTO;
import demo.penibilite.backend.entities.Employeur;
import demo.penibilite.backend.mappers.EmployeurMapper;

import java.util.List;


@Service
@Transactional
public class EmployeurService implements IEmployeurService {

    private final EmployeurRepository employeurRepository;
    private final EmployeurMapper employeurMapper;

    public EmployeurService(EmployeurRepository employeurRepository, EmployeurMapper employeurMapper) {
        this.employeurRepository = employeurRepository;
        this.employeurMapper = employeurMapper;
    }

    @Override
    public EmployeurDTO creerEmployeur(Employeur employeur) {
        Employeur saved = employeurRepository.save(employeur);
        return employeurMapper.toDto(saved);
    }

    @Override
    public EmployeurDTO getBySiret(String siret) {
        Employeur employeur = employeurRepository.findBySiret(siret)
                .orElseThrow(() -> new RuntimeException("Employeur introuvable"));
        return employeurMapper.toDto(employeur);
    }

    @Override
    public List<EmployeurDTO> getAll() {
        return employeurMapper.toDtoList(employeurRepository.findAll());
    }

    @Override
	public EmployeurDTO getByEmail(String email) {
		Employeur employeur = employeurRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Employeur introuvable"));
        return employeurMapper.toDto(employeur);
	}
}



