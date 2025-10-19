package demo.penibilite.backend.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.AgentRepository;
import demo.penibilite.backend.dto.AgentDTO;
import demo.penibilite.backend.entities.Agent;
import demo.penibilite.backend.mappers.AgentMapper;


import java.util.List;


@Service
@Transactional
public class AgentService implements IAgentService {

    private final AgentRepository agentRepository;
    private final AgentMapper agentMapper;

    public AgentService(AgentRepository agentRepository, AgentMapper agentMapper) {
        this.agentRepository = agentRepository;
        this.agentMapper = agentMapper;
    }

    @Override
    public AgentDTO creerAgent(Agent agent) {
        Agent saved = agentRepository.save(agent);
        return agentMapper.toDto(saved);
    }

    @Override
    public AgentDTO getByEmail(String email) {
        Agent agent = agentRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Agent introuvable"));
        return agentMapper.toDto(agent);
    }

    @Override
    public List<AgentDTO> getAll() {
        return agentMapper.toDtoList(agentRepository.findAll());
    }

    
}

