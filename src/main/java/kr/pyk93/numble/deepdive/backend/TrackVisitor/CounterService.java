package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CounterService {
	
	private final CounterRepository repository;
	
	@Transactional
	public Long registerCounter(String url){
		CounterEntity counter = new CounterEntity(url);
		repository.save(counter);
		
		return counter.getId();
	}
	
	@Transactional
	public Long addCounter(String url){
		return repository.findByUrl(url).orElseThrow().increase();
	}
	
	
	public Long getCounter(String url){
		return repository.findByUrl(url).orElseThrow().getCount();
	}
}
