package springboot.crawler.service;

/**
 * @author renhuibo  2019-08-13 00:11:22
 * @Description
 */
public interface CrawlerService {
	
	void start(int thread,int depth,boolean endpoint);
	
}
