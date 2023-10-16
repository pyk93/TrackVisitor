package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface CounterRepository extends JpaRepository<CounterEntity, Long> {
	Optional<CounterEntity> findByUrlAndCountDate(@Param("url") String url, @Param("countDate") LocalDate date);
	List<CounterEntity> findByUrl(@Param("url") String url);

	@Query("SELECT new kr.pyk93.numble.deepdive.backend.TrackVisitor.CountTotalDTO(:url,COALESCE(SUM(e.count),0)) FROM CounterEntity e WHERE e.url = :url" )
	Optional<CountTotalDTO> findTotalCount(@Param("url") String url);

}
