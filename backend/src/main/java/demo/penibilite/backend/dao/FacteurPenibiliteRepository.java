package demo.penibilite.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.penibilite.backend.entities.FacteurPenibilite;

public interface FacteurPenibiliteRepository extends JpaRepository<FacteurPenibilite, Long> {
    Optional<FacteurPenibilite> findByCode(String code);
    Optional<FacteurPenibilite> findByLibelle(String libelle);
}