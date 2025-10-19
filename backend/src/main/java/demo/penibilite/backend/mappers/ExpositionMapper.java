package demo.penibilite.backend.mappers;

import org.mapstruct.*;

import demo.penibilite.backend.dto.ExpositionDTO;
import demo.penibilite.backend.entities.Exposition;

import java.util.List;

@Mapper(componentModel = "spring", uses = {SalarieMapper.class, EmployeurMapper.class, FacteurPenibiliteMapper.class, AgentMapper.class})
public interface ExpositionMapper {

    ExpositionDTO toDto(Exposition exposition);

    List<ExpositionDTO> toDtoList(List<Exposition> expositions);

    Exposition toEntity(ExpositionDTO expositionDTO);
}
