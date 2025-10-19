package demo.penibilite.backend.mappers;

import org.mapstruct.*;

import demo.penibilite.backend.dto.FacteurPenibiliteDTO;
import demo.penibilite.backend.entities.FacteurPenibilite;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FacteurPenibiliteMapper {
    FacteurPenibiliteDTO toDto(FacteurPenibilite facteur);
    List<FacteurPenibiliteDTO> toDtoList(List<FacteurPenibilite> facteurs);
    FacteurPenibilite toEntity(FacteurPenibiliteDTO dto);
}
