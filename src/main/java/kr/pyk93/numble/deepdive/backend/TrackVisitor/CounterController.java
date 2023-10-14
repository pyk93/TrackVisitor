package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class CounterController {
	
	private final CounterService service;
	
	@GetMapping("/registerCounter/{url}")
	public String registerCounter(@PathVariable String url) {
		service.registerCounter(url);
		return url;
	}
	
	
	@GetMapping("/addCount/{url}")
	public Long add(@PathVariable String url) {
		
		return service.addCounter(url);
		
	}
	@GetMapping("/getCount/{url}")
	public Long getCount(@PathVariable String url) {
		
		return service.getCounter(url);
		
	}
}
