package demo.penibilite.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.penibilite.backend.entities.Employeur;

public interface EmployeurRepository extends JpaRepository<Employeur, Long> {
    Optional<Employeur> findBySiret(String siret);
	Optional<Employeur> findByEmail(String email);
}
