package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

public interface KeyRepository extends JpaRepository<KeyEntity, Long> {
	Optional<CounterEntity> findByUrlAndKey(@Param("url") String url, @Param("key") String key);
}
