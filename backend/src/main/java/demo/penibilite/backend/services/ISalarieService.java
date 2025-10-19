package demo.penibilite.backend.services;

import java.util.List;

import demo.penibilite.backend.dto.SalarieDTO;
import demo.penibilite.backend.entities.Salarie;



public interface ISalarieService {
	
	public SalarieDTO creerSalarie(Salarie salarie);
	    
    public SalarieDTO getByEmail(String email);

    public SalarieDTO getByNumeroSecu(String numeroSecu);

    public List<SalarieDTO> getAll();

}
