package kr.pyk93.numble.deepdive.backend.TrackVisitor;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import io.swagger.annotations.Api;
import io.swagger.annotations.*;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

@Api(tags = "Basic Count API")
@RestController
@RequiredArgsConstructor
public class CounterController {

	static final int NUMBER_OF_DAYS_RETURN = 7;

	@Autowired
	private final CounterService service;
	
	@Autowired
	private final KeyCheckService keyService;

	
    private final DateTime dateTime;


	@GetMapping("/registerCounter/{url}")
	public String registerCounter(@PathVariable String url, @RequestParam(value="key") String key) {
		//service.registerCounter(url);
		return url;
	}

	@Operation(summary = "카운트 증가", description = "카운터를 1회 증가시킵니다. key로 인증이 필요합니다.")
	@PostMapping("/addCount/{url}")
	public Long add(@RequestBody String url, @RequestParam(value="key") String key) {
		if(keyService.isCorrectKey(url, key)==false)
			return 0L;
		service.addCounter(url);
		return 0L;

	}
	@GetMapping("/getCountTotal/{url}")
	@Operation(summary = "총 방문자수 정보", description = "총 방문자수를 리턴합니다.")
	public CountTotalDTO getCountTotal(@PathVariable String url, @RequestParam(value="key") String key) {

		return service.getCounter(url);
	}
	@GetMapping("/getCountToday/{url}")
	@Operation(summary = "오늘 방문자수 정보", description = "오늘 방문자수를 리턴합니다.")

	public CountDayDTO getCountToday(@PathVariable String url, @RequestParam(value="key") String key) {

		return service.getCounterDay(url, dateTime.today());

	}

	@ResponseBody
	@GetMapping("/getCount/{url}")
	@Operation(summary = "방문자수 정보", description = "총 방문자수와 오늘 방문자수를 리턴합니다.")
	public Map<String, Object> getCount(@PathVariable String url, @RequestParam(value="key") String key)
	{
		CountTotalDTO total = getCountTotal(url, key);
		CountDayDTO today = getCountToday(url, key);
		Map<String,Object> retVal = new HashMap<String,Object>();

		retVal.put("today", today.getCount());
		retVal.put("total", total.getCount());
		

		return retVal;
	}

	@ResponseBody
	@GetMapping("/weeklyStatics/{url}")
	@Operation(summary = "방문자수 통계", description = "일정 기간동안의 방문자수 통계를 리턴합니다.")
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
