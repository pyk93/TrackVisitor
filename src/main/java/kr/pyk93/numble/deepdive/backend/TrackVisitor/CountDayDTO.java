package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CountDayDTO {
	private final String url;
	private final Long count;
	private final LocalDate date;
}
