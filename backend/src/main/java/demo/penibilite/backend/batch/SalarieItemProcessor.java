package demo.penibilite.backend.batch;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import demo.penibilite.backend.dao.ExpositionRepository;
import demo.penibilite.backend.entities.Exposition;
import demo.penibilite.backend.entities.Salarie;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalarieItemProcessor implements ItemProcessor<Salarie, Salarie> {

    private final ExpositionRepository expositionRepository;

    @Override
    public Salarie process(Salarie salarie) throws Exception {
        List<Exposition> expositions = expositionRepository.expositionsValidesBySalarie(salarie.getId());

        double totalPoints = expositions.stream()
                .mapToDouble(e -> e.getJourPenibles() * e.getFacteur().getCoefficient())
                .sum();
        totalPoints = Math.round(totalPoints * 100) / 100.0 ; 
        salarie.setTotalPoints(totalPoints);
        salarie.setDateLastBatchUpdate(LocalDateTime.now());

        return salarie;
    }

  
}

