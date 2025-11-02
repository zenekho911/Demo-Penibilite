package demo.penibilite.backend.services;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import demo.penibilite.backend.dao.AgentRepository;
import demo.penibilite.backend.dto.AgentDTO;
import demo.penibilite.backend.entities.Agent;
import demo.penibilite.backend.mappers.AgentMapper;


import java.util.List;


@Service
@Transactional
public class AgentService implements IAgentService {

	private final AgentRepository agentRepository;
	private final AgentMapper agentMapper;
	private final JobLauncher jobLauncher;
	private final Job updateSalariePointsJob;

	public AgentService(AgentRepository agentRepository, AgentMapper agentMapper, JobLauncher jobLauncher, Job updateSalariePointsJob ) {
		this.agentRepository = agentRepository;
		this.agentMapper = agentMapper;
		this.jobLauncher = jobLauncher;
		this.updateSalariePointsJob = updateSalariePointsJob;
	}

	@Override
	public AgentDTO creerAgent(Agent agent) {
		Agent saved = agentRepository.save(agent);
		return agentMapper.toDto(saved);
	}

	@Override
	public AgentDTO getByEmail(String email) {
		Agent agent = agentRepository.findByEmail(email)
				.orElseThrow(() -> new RuntimeException("Agent introuvable"));
		return agentMapper.toDto(agent);
	}

	@Override
	public List<AgentDTO> getAll() {
		return agentMapper.toDtoList(agentRepository.findAll());
	}

	@Transactional(propagation = Propagation.NOT_SUPPORTED)
	@Override
	public String updateSalariesPointsBatch() {
		JobParameters params = new JobParametersBuilder()
				.addLong("startAt", System.currentTimeMillis())
				.toJobParameters();

		try { jobLauncher.run(updateSalariePointsJob, params); } 
		catch (Exception e) { e.printStackTrace(); }
		return "Batch lancé avec succès !";
	}
	
	
	



}

