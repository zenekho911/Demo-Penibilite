package demo.penibilite.backend.services;

import java.util.List;

import demo.penibilite.backend.dto.EmployeurDTO;
import demo.penibilite.backend.entities.Employeur;



public interface IEmployeurService {
	
	public EmployeurDTO creerEmployeur(Employeur employeur);

    public EmployeurDTO getBySiret(String siret);

    public List<EmployeurDTO> getAll();
    
	public EmployeurDTO getByEmail(String email);

}
