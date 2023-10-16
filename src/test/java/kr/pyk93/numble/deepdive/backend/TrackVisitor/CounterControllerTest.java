package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

//@WebMvcTest(CounterController.class)
@ExtendWith(MockitoExtension.class)
public class CounterControllerTest {

    @Mock
    private CounterService counterService;
    
    @InjectMocks
    private CounterController counterController;
    
    @Mock
    private DateTime testTime;
    
    private MockMvc mockMvc;
    
    @BeforeEach
    public void init() {
    	mockMvc = MockMvcBuilders.standaloneSetup(counterController)
    			.build();
    }
    
    @Test
    void testBasic() throws Exception {
    	final String testUrl = "abcde";
    	final LocalDate testDate = LocalDate.of(2023, 1, 1);
    	given(counterService.getCounter(testUrl)).willReturn(new CountTotalDTO(testUrl,0L));
    	given(counterService.getCounterDay(testUrl,testDate)).willReturn(new CountDayDTO(testUrl,0L,testDate));
    	given(testTime.today()).willReturn(testDate);
    	
    	
    	mockMvc.perform(get("/getCount/"+testUrl))
    		.andExpect(status().isOk())
    		.andExpect(jsonPath("$.today").exists())
    		.andExpect(jsonPath("$.total").exists())
    		.andExpect(jsonPath("$.today").value(0))
    		.andExpect(jsonPath("$.total").value(0));
    	
    	mockMvc.perform(get("/addCount/"+testUrl))
    		.andExpect(status().isOk());
    	
    	mockMvc.perform(get("/getCount/"+testUrl))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.today").exists())
		.andExpect(jsonPath("$.total").exists());
    	
    	mockMvc.perform(get("/weeklyStatics/"+testUrl))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.url").value(testUrl))
		.andExpect(jsonPath("$.statics").exists());
    }


}
