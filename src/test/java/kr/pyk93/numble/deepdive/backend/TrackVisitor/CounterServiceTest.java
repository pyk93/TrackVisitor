package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CounterServiceTest {
	
	@Mock
	private CounterRepository testCounterRepository;
	
    @InjectMocks
    private CounterService testCounterService;
	
	@Test
	void testAddCounter() {
		//given
		final String testUrl = "12345";
		given(testCounterRepository.findByUrl(testUrl)).willReturn(Optional.of(new CounterEntity(testUrl)));
		
		//when
		Long counter = testCounterService.getCounter(testUrl);
		
		Long counter2 = testCounterService.addCounter(testUrl);
		Long counter3 = testCounterService.getCounter(testUrl);
		Long counter4 = testCounterService.addCounter(testUrl);

		
		
		//then
		assertEquals(0L, counter);
		assertEquals(1L, counter2);
		assertEquals(1L, counter3);
		assertEquals(2L, counter4);
	}
	
}
