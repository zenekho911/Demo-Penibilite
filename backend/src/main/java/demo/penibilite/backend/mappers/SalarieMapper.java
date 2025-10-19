package demo.penibilite.backend.mappers;

import org.mapstruct.*;

import demo.penibilite.backend.dto.SalarieDTO;
import demo.penibilite.backend.entities.Salarie;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalarieMapper {
    SalarieDTO toDto(Salarie salarie);
    List<SalarieDTO> toDtoList(List<Salarie> salaries);
    Salarie toEntity(SalarieDTO dto);
}
