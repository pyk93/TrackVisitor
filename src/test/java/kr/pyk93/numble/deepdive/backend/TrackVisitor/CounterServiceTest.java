package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class CounterServiceTest {

	@Mock
	private CounterRepository testCounterRepository;

    @InjectMocks
    private CounterService testCounterService;
    
    @Mock
    private DateTime testTime;

	@Test
	void testAddCounter() {
		//given
		final String testUrl = "12345";
		final LocalDate testDate = LocalDate.of(2023, 1, 1);
		
		
		given(testTime.today()).willReturn(testDate);
		CounterEntity entity = new CounterEntity(testUrl,testDate);

		given(testCounterRepository.save(any()))
			.will(invocation -> { return entity; });
		given(testCounterRepository.findTotalCount(testUrl))
			.will(invocation -> { return Optional.of(new CountTotalDTO(testUrl,entity.getCount()));});
		given(testCounterRepository.findByUrlAndCountDate(testUrl, testDate))
			.will(invocation -> { return Optional.of(entity); });

		//when

		CountTotalDTO counter = testCounterService.getCounter(testUrl);
		CountDayDTO counter2 = testCounterService.getCounterDay(testUrl, testDate);

		testCounterService.addCounter(testUrl);

		CountTotalDTO counter3 = testCounterService.getCounter(testUrl);
		CountDayDTO counter4 = testCounterService.getCounterDay(testUrl, testDate);
		//then

		assertEquals(0L, counter.getCount());
		assertEquals(0L, counter2.getCount());
		assertEquals(1L, counter3.getCount());
		assertEquals(1L, counter4.getCount());
	}
	@Test
	void testAddCounter2() {
		//given
		final String testUrl = "12345";
		final LocalDate testDate = LocalDate.now();//LocalDate.of(2023, 1, 1);
		CounterEntity entity = new CounterEntity(testUrl,testDate);

		//given(testCounterRepository.findByUrlAndCountDate(testUrl,testDate)).willReturn(Optional.empty());//Optional.of(new CounterEntity(testUrl,testDate)));
		given(testCounterRepository.save(any()))
			.will(invocation -> { return entity; });
		given(testCounterRepository.findTotalCount(testUrl))
			.will(invocation -> { return Optional.of(new CountTotalDTO(testUrl,entity.getCount()));});
		//when



		testCounterService.addCounter(testUrl);
		CountTotalDTO counter = testCounterService.getCounter(testUrl);
		testCounterService.addCounter(testUrl);
		CountTotalDTO counter3 = testCounterService.getCounter(testUrl);
		//then

		assertEquals(1L, counter.getCount());
		//assertEquals(1L, counter2);
		assertEquals(2L, counter3.getCount());
		//assertEquals(2L, counter4);
	}

	@Test
	void testGetCountEmpty() {
		//given
		final String testUrl = "12345";
		final LocalDate testDate = LocalDate.now();//LocalDate.of(2023, 1, 1);
		CounterEntity entity = new CounterEntity(testUrl,testDate);

		given(testCounterRepository.findByUrlAndCountDate(testUrl, testDate))
			.willReturn(Optional.empty());
		given(testCounterRepository.findTotalCount(testUrl))
			.willReturn(Optional.empty());

		CountTotalDTO counter = testCounterService.getCounter(testUrl);
		CountDayDTO counter2 = testCounterService.getCounterDay(testUrl,testDate);

		assertEquals(0L, counter.getCount());
		assertEquals(0L, counter2.getCount());
	}




}
