package demo.penibilite.backend.services;

import java.util.List;

import demo.penibilite.backend.dto.FacteurPenibiliteDTO;
import demo.penibilite.backend.entities.FacteurPenibilite;



public interface IFacteurPenibiliteService {
	
	
	public FacteurPenibiliteDTO creerFacteur(FacteurPenibilite facteur);

    public FacteurPenibiliteDTO getByCode(String code);

    public List<FacteurPenibiliteDTO> getAll();

}
