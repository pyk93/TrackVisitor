package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class KeyCheckService {
	private final KeyRepository repository;
	public boolean isCorrectKey(String url, String key) {
		Optional<CounterEntity> searchResult = repository.findByUrlAndKey(url,key);
		return searchResult.isPresent();
	}
}
