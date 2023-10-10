package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "people", path = "people")
public interface CounterRepository extends CrudRepository<Counter, Long> {
	List<Counter> findByUrl(@Param("url") String url);

	Counter findById(long id);

}
