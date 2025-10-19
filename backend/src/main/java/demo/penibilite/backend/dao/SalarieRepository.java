package demo.penibilite.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.penibilite.backend.entities.Salarie;

public interface SalarieRepository extends JpaRepository<Salarie, Long> {
    Optional<Salarie> findByNumeroSecu(String numeroSecu);
    Optional<Salarie> findByNomAndPrenom(String nom, String prenom);
	Optional<Salarie> findByEmail(String email);
}


