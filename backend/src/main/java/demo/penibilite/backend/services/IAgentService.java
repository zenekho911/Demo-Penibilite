package demo.penibilite.backend.services;

import java.util.List;

import demo.penibilite.backend.dto.AgentDTO;
import demo.penibilite.backend.entities.Agent;



public interface IAgentService {
	
    public AgentDTO creerAgent(Agent agent);

    public AgentDTO getByEmail(String email);

    public List<AgentDTO> getAll();

}
