package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

@Component
public class DateTime {
	public LocalDate today() {
		return LocalDate.now();
	}
}
