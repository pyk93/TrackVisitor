package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounterService {

	private final CounterRepository repository;
	
	
    private final DateTime dateTime;
	
	@Transactional
	public void addCounter(String url){
		LocalDate date = dateTime.today();
		CounterEntity counter = repository.findByUrlAndCountDate(url,date).orElseGet(()->createCounter(url,date));
		counter.increase();
		repository.save(counter);
	}

	private CounterEntity createCounter(String url, LocalDate date) {
		CounterEntity counter = new CounterEntity(url,date);
		return repository.save(counter);

	}


	public CountTotalDTO getCounter(String url){
		return repository.findTotalCount(url)
				.map(count-> new CountTotalDTO(url,count.getCount()))
				.orElseGet(()->new CountTotalDTO(url,0L));
	}

	public CountDayDTO getCounterDay(String url, LocalDate date) {
		return repository.findByUrlAndCountDate(url, date)
				.map(count -> new CountDayDTO(url,count.getCount(), date))
				.orElseGet(()->new CountDayDTO(url,0L,date));
	}
}
