package demo.penibilite.backend.batch;

import java.util.Collections;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.builder.RepositoryItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.transaction.PlatformTransactionManager;

import demo.penibilite.backend.dao.SalarieRepository;
import demo.penibilite.backend.entities.Salarie;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class BatchConfiguration {

    private final SalarieRepository salarieRepository;
    private final SalarieItemProcessor salarieItemProcessor;
    private final SalarieItemWriter salarieItemWriter;
    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    ItemReader<Salarie> salarieItemReader() {
        return new RepositoryItemReaderBuilder<Salarie>()
                .repository(salarieRepository)
                .methodName("findAll")
                .pageSize(100)
                .sorts(Collections.singletonMap("id", Sort.Direction.ASC))
                .name("salarieItemReader")
                .build();
    }

    @Bean
    Step updateSalariePointsStep() {
        return new StepBuilder("updateSalariePointsStep", jobRepository)
                .<Salarie, Salarie>chunk(100, transactionManager)
                .reader(salarieItemReader())
                .processor(salarieItemProcessor)
                .writer(salarieItemWriter)
                .build();
    }

    @Bean
    Job updateSalariePointsJob() {
        return new JobBuilder("updateSalariePointsJob", jobRepository)
                .start(updateSalariePointsStep())
                .build();
    }
}


