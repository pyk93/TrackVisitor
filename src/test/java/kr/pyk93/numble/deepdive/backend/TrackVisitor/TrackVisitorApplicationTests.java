package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TrackVisitorApplicationTests {

	@Autowired
	private CounterController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}

}
