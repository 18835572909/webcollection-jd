package springboot.crawler.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springboot.crawler.service.CrawlerService;

/**
 * @author renhuibo  2019-08-12 23:27:45
 * @Description
 */
@RestController
public class TestController {

	@Autowired
	private CrawlerService service;
	
	
	@RequestMapping("/ping")
	public Map<String, String> ping(){
		Map<String, String> map = new HashMap<>();
		map.put("ping", "pong");
		return map;
	}
	
	@RequestMapping("/start")
	public String start(int thread, int depth, boolean endpoint) {
		long start = System.currentTimeMillis();
		service.start(thread, depth, endpoint);
		long s = (System.currentTimeMillis()-start )/1000;
		return "用时："+s+"s";
	}
}
