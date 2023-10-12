package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class CounterController {
	
	private final CounterRepository repository;
	
	CounterController(CounterRepository repository)
	{
		this.repository = repository;
	}
	

	@GetMapping("/test/{url}")
	public Counter test(@PathVariable String url) {
		
		return repository.findByUrl(url).orElseGet(()->{
			Counter c = new Counter(url);
			return repository.save(c);
		});
		
	}
	
	
	@GetMapping("/add/{url}")
	public Long add(@PathVariable String url) {
		
		return repository.findByUrl(url).map(cnt -> {
			cnt.increase();
			return repository.save(cnt);
			}).orElseThrow().getCount();
		
	}
	@GetMapping("/getCount/{url}")
	public Long getCount(@PathVariable String url) {
		
		return repository.findByUrl(url).orElseThrow().getCount();
		
	}
}
