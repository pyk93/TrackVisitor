package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class CounterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long count;
	private String url;

	protected CounterEntity() {
	}

	public CounterEntity(String url) {
		this.url = url;
		this.count = (long) 0;
	}

	public Long getId() {
		return id;
	}

	public Long getCount() {
		return count;
	}

	public String getUrl() {
		return url;
	}
	
	public Long increase() {
		count += 1;
		return count;
	}

}
