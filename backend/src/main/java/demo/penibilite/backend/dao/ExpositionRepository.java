package demo.penibilite.backend.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import demo.penibilite.backend.entities.Exposition;
import demo.penibilite.backend.entities.ExpositionStatus;

public interface ExpositionRepository extends JpaRepository<Exposition, Long> {

    // Rechercher par salarié
    List<Exposition> findBySalarieId(Long salarieId);

    // Rechercher par employeur
    List<Exposition> findByEmployeurId(Long employeurId);

    // Rechercher par facteur
    List<Exposition> findByFacteurId(Long facteurId);

    // Rechercher par statut
    List<Exposition> findByStatus(ExpositionStatus status);

    // Rechercher par agent
    List<Exposition> findByValideParId(Long agentId);

    // Combinaisons utiles
    List<Exposition> findBySalarieIdAndStatus(Long salarieId, ExpositionStatus status);
    List<Exposition> findByEmployeurIdAndStatus(Long employeurId, ExpositionStatus status);
    
    // liste des expositions validés pour un salarié donné : elle sert au batch
    @Query("SELECT e FROM Exposition e WHERE e.salarie.id = :salarieId AND e.status = 'VALIDATED'")
    List<Exposition> expositionsValidesBySalarie(@Param("salarieId") Long salarieId);
}
