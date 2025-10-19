package demo.penibilite.backend.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import demo.penibilite.backend.dto.AgentDTO;
import demo.penibilite.backend.entities.Agent;

@Mapper(componentModel = "spring")
public interface AgentMapper {
    AgentDTO toDto(Agent agent);
    List<AgentDTO> toDtoList(List<Agent> agents);
    Agent toEntity(AgentDTO dto);
}
