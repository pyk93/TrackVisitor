package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
public class CounterEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private Long count;
	private String url;
	@Getter
	private LocalDate countDate;

	protected CounterEntity() {
	}

	public CounterEntity(String url, LocalDate date) {
		this.url = url;
		this.count = (long) 0;
		this.countDate = date;
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
