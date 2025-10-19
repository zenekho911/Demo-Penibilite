package demo.penibilite.backend.services;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import demo.penibilite.backend.dao.*;
import demo.penibilite.backend.entities.*;

import java.time.LocalDate;

@Component
public class DataSeeder implements CommandLineRunner {

    private final SalarieRepository salarieRepository;
    private final EmployeurRepository employeurRepository;
    private final AgentRepository agentRepository;
    private final FacteurPenibiliteRepository facteurRepository;
    private final ExpositionRepository expositionRepository;

    public DataSeeder(SalarieRepository salarieRepository,
                      EmployeurRepository employeurRepository,
                      AgentRepository agentRepository,
                      FacteurPenibiliteRepository facteurRepository,
                      ExpositionRepository expositionRepository) {
        this.salarieRepository = salarieRepository;
        this.employeurRepository = employeurRepository;
        this.agentRepository = agentRepository;
        this.facteurRepository = facteurRepository;
        this.expositionRepository = expositionRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // --- Salariés fictifs ---
        Salarie salarie1 = Salarie.builder()
                .username("user_salarie1")
				.nom("Dupont")
				.prenom("Jean")
				.numeroSecu("1234567890123")
				.email("j.dupont@salarie.test")
				.telephone("0712345678")
				.dateNaissance(LocalDate.ofYearDay(1971, 218))
				.build();
        Salarie salarie2 = Salarie.builder()
                .username("user_salarie2")
				.nom("Dupont")
				.prenom("Franck")
				.numeroSecu("9876543210987")
				.email("f.dupont@salarie.test")
				.telephone("0698765432")
				.dateNaissance(LocalDate.ofYearDay(1987, 300))
				.build();
		Salarie salarie3 = Salarie.builder()
                .username("user_salarie3")
				.nom("Dupont")
				.prenom("Huguette")
				.numeroSecu("5555543210987")
				.email("h.dupont@salarie.test")
				.telephone("0645685212")
				.dateNaissance(LocalDate.ofYearDay(1969, 115))
				.build();
        salarieRepository.save(salarie1);
        salarieRepository.save(salarie2);
		salarieRepository.save(salarie3);

        // --- Employeurs fictifs ---
        Employeur employeur1 = Employeur.builder()
				.username("user_employeur1")
                .raisonSociale("ABC SARL")
				.siret("11122233344455")
				.email("abc.sarl@company.test")
				.telephone("0411223344")
				.adresse("Marseille")
				.build();
        Employeur employeur2 = Employeur.builder()
				.username("user_employeur2")
                .raisonSociale("XYZ SAS")
				.siret("55566677788899")
				.email("xyz.sas@company.test")
				.telephone("0455667788")
				.adresse("Ajaccio")
				.build();
		Employeur employeur3 = Employeur.builder()
				.username("user_employeur3")
                .raisonSociale("KLM CP")
				.siret("55521356489788")
				.email("klm.cp@company.test")
				.telephone("0414725836")
				.adresse("Aix-en-Provence")
				.build();
        employeurRepository.save(employeur1);
        employeurRepository.save(employeur2);
		employeurRepository.save(employeur3);

        // --- Agents fictifs ---
        Agent agent1 = Agent.builder()
				.username("user_agent1")
                .nomComplet("John Smith")
				.email("j.smith@agent.test")
				.telephone("0798765432")
				.actif(true)
				.dateCreation(LocalDate.ofYearDay(2010, 51).atStartOfDay())
				.build();
        Agent agent2 = Agent.builder()
				.username("user_agent2")
                .nomComplet("Rebecca Smith")
				.email("r.smith@agent.test")
				.telephone("0655448866")
				.actif(true)
				.dateCreation(LocalDate.ofYearDay(2015, 33).atStartOfDay())
				.build();
        agentRepository.save(agent1);
        agentRepository.save(agent2);

        // --- Facteurs de pénibilité ---
        FacteurPenibilite facteur1 = FacteurPenibilite.builder()
                .code("FP01")
				.libelle("POSTURE PENIBLE")
				.description("Postures contraignantes plus de 7h/jour")
				.coefficient(0.2)
				.build();
        		
        FacteurPenibilite facteur2 = FacteurPenibilite.builder()
                .code("FP02")
				.libelle("TRAVAIL DE NUIT")
				.description("Travail nocturne plus de 7h/jour")
				.coefficient(0.4)
				.build();
		FacteurPenibilite facteur3 = FacteurPenibilite.builder()
                .code("FP03")
				.libelle("TEMPERATURE EXTREME")
				.description("Travail sous extreme temperature  [-0, +37]° plus de 5h/jour")
				.coefficient(0.6)
				.build();
		FacteurPenibilite facteur4 = FacteurPenibilite.builder()
                .code("FP04")
				.libelle("EXPOSITION SONORE")
				.description("Exposition sonore [75-80] dB  plus de 4h/jour")
				.coefficient(0.8)
				.build();
		FacteurPenibilite facteur5 = FacteurPenibilite.builder()
                .code("FP05")
				.libelle("EXPOSITION CHIMIQUE")
				.description("Exposition aux radiations plus de 1h/jour")
				.coefficient(1)
				.build();
		
        facteurRepository.save(facteur1);
        facteurRepository.save(facteur2);
		facteurRepository.save(facteur3);
		facteurRepository.save(facteur4);
		facteurRepository.save(facteur5);

        // --- Expositions (simulation aléatoire) ---
       
        Exposition exposition1 = Exposition.builder()
        		.dateDeclaration(LocalDate.of(2025, 9, 1).atTime(8, 40))
        		.periode("2025-08")
				.jourPenibles(17)
				.status(ExpositionStatus.VALIDATED)
                .salarie(salarie1)
                .employeur(employeur1)
                .facteur(facteur2)
                .validePar(agent1)
                .dateValidation(LocalDate.of(2025, 9, 1).atTime(9, 10))
                .commentaireAgent("Validé OK")
                .build();
		
		Exposition exposition2 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 9, 1).atTime(8, 43))
				.periode("2025-08")
				.jourPenibles(11)
                .status(ExpositionStatus.VALIDATED)
                .salarie(salarie1)
                .employeur(employeur1)
                .facteur(facteur1)             
                .validePar(agent2)
                .dateValidation(LocalDate.of(2025, 9, 1).atTime(9, 11))
                .commentaireAgent("OK")
                .build();
				
		Exposition exposition3 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 9, 2).atTime(8, 30))
				.periode("2025-08")
				.jourPenibles(15)
				.status(ExpositionStatus.VALIDATED)
                .salarie(salarie2)
                .employeur(employeur2)
                .facteur(facteur2)
                .validePar(agent1)
                .dateValidation(LocalDate.of(2025, 9, 2).atTime(14, 22))
                .commentaireAgent("Validé")
                .build();
				
		Exposition exposition4 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 9, 2).atTime(9, 30))
				.periode("2025-08")
				.jourPenibles(21)
                .status(ExpositionStatus.VALIDATED)
                .salarie(salarie2)
                .employeur(employeur2)
                .facteur(facteur3)               
                .validePar(agent1)
                .dateValidation(LocalDate.of(2025, 9, 2).atTime(11, 39))
                .commentaireAgent("Validé OK")
                .build();
				
		Exposition exposition5 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 9, 1).atTime(8, 30))
				.periode("2025-08")
				.jourPenibles(8)
                .status(ExpositionStatus.VALIDATED)
                .salarie(salarie3)
                .employeur(employeur3)
                .facteur(facteur4)            
                .validePar(agent2)
                .dateValidation(LocalDate.of(2025, 9, 1).atTime(8, 45))
                .commentaireAgent("OK")
                .build();
		
		Exposition exposition6 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 10, 3).atTime(9, 0))
				.periode("2025-09")
				.jourPenibles(8)
				.status(ExpositionStatus.PENDING)
                .salarie(salarie1)
                .employeur(employeur1)
                .facteur(facteur1)
                .build();
		
		Exposition exposition7 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 10, 2).atTime(10, 48))
				.periode("2025-09")
				.jourPenibles(4)
				.status(ExpositionStatus.PENDING)
                .salarie(salarie1)
                .employeur(employeur1)
                .facteur(facteur2)
                .build();
				
		Exposition exposition8 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 10, 1).atTime(8, 55))
				.periode("2025-09")
				.jourPenibles(6)
				.status(ExpositionStatus.PENDING)
                .salarie(salarie2)
                .employeur(employeur2)
                .facteur(facteur2)
                .build();
				
		Exposition exposition9 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 10, 3).atTime(15, 11))
				.periode("2025-09")
				.jourPenibles(19)
				.status(ExpositionStatus.PENDING)
                .salarie(salarie2)
                .employeur(employeur2)
                .facteur(facteur3)
                .build();
				
		Exposition exposition10 = Exposition.builder()
				.dateDeclaration(LocalDate.of(2025, 10, 1).atTime(8, 30))
				.periode("2025-09")
				.jourPenibles(14)
				.status(ExpositionStatus.PENDING)
                .salarie(salarie3)
                .employeur(employeur3)
                .facteur(facteur4)
                .build();
				

        expositionRepository.save(exposition1);
        expositionRepository.save(exposition2);
		expositionRepository.save(exposition3);
        expositionRepository.save(exposition4);
		expositionRepository.save(exposition5);
        expositionRepository.save(exposition6);
		expositionRepository.save(exposition7);
        expositionRepository.save(exposition8);
		expositionRepository.save(exposition9);
        expositionRepository.save(exposition10);

        System.out.println("Database seeded successfully!");
    }
}


