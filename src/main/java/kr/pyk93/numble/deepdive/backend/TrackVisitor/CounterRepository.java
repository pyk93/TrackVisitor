package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface CounterRepository extends JpaRepository<CounterEntity, Long> {
	Optional<CounterEntity> findByUrl(@Param("url") String url);

}
