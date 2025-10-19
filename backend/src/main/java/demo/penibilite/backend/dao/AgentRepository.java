package demo.penibilite.backend.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import demo.penibilite.backend.entities.Agent;

public interface AgentRepository extends JpaRepository<Agent, Long> {
    Optional<Agent> findByUsername(String username);
    Optional<Agent> findByEmail(String email);
}