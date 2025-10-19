package demo.penibilite.backend.mappers;

import org.mapstruct.*;

import demo.penibilite.backend.dto.EmployeurDTO;
import demo.penibilite.backend.entities.Employeur;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EmployeurMapper {
    EmployeurDTO toDto(Employeur employeur);
    List<EmployeurDTO> toDtoList(List<Employeur> employeurs);
    Employeur toEntity(EmployeurDTO dto);
}
