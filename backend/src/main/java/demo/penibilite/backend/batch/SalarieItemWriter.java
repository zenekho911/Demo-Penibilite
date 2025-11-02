package demo.penibilite.backend.batch;



import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import demo.penibilite.backend.dao.SalarieRepository;
import demo.penibilite.backend.entities.Salarie;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SalarieItemWriter implements ItemWriter<Salarie> {

    private final SalarieRepository salarieRepository;


	@Override
	public void write(Chunk<? extends Salarie> chunk) throws Exception {
		salarieRepository.saveAll(chunk);
		
	}
}

