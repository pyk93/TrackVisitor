package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
public class CounterController {

	static final int NUMBER_OF_DAYS_RETURN = 7;

	@Autowired
	private final CounterService service;

	
    private final DateTime dateTime;


	@GetMapping("/registerCounter/{url}")
	public String registerCounter(@PathVariable String url) {
		//service.registerCounter(url);
		return url;
	}


	@GetMapping("/addCount/{url}")
	public Long add(@PathVariable String url) {

		service.addCounter(url);
		return 0L;

	}
	@GetMapping("/getCountTotal/{url}")
	public CountTotalDTO getCountTotal(@PathVariable String url) {

		return service.getCounter(url);
	}
	@GetMapping("/getCountToday/{url}")
	public CountDayDTO getCountToday(@PathVariable String url) {

		return service.getCounterDay(url, dateTime.today());

	}

	@ResponseBody
	@GetMapping("/getCount/{url}")
	public Map<String, Object> getCount(@PathVariable String url)
	{
		CountTotalDTO total = getCountTotal(url);
		CountDayDTO today = getCountToday(url);
		Map<String,Object> retVal = new HashMap<String,Object>();

		retVal.put("today", today.getCount());
		retVal.put("total", total.getCount());
		

		return retVal;
	}

	@ResponseBody
	@GetMapping("/weeklyStatics/{url}")
	public Map<String,Object> getWeeklyStatics(@PathVariable String url)
	{

		@SuppressWarnings("unchecked")
		Map<String,Object>[] statics = (Map<String,Object>[])new Map[NUMBER_OF_DAYS_RETURN];
		LocalDate today = dateTime.today();

		for(int i=0;i<NUMBER_OF_DAYS_RETURN; i++)
		{
			
			CountDayDTO obj = service.getCounterDay(url, today.minusDays(i));
			statics[NUMBER_OF_DAYS_RETURN - 1 - i] = new HashMap<String,Object>();
			statics[NUMBER_OF_DAYS_RETURN - 1 - i].put("date", obj.getDateStr());
			statics[NUMBER_OF_DAYS_RETURN - 1 - i].put("count", obj.getCount());
		}
		Map<String,Object> retVal = new HashMap<String,Object>();
		retVal.put("url", url);
		retVal.put("statics", statics);
		return retVal;
	}
}
