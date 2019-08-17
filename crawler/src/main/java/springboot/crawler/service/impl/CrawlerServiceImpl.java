package springboot.crawler.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import springboot.crawler.processor.JdBookCrawler;
import springboot.crawler.rep.CrawlerRepository;
import springboot.crawler.service.CrawlerService;

/**
 * @author renhuibo  2019-08-13 00:11:42
 * @Description
 */
@Service
@Transactional
public class CrawlerServiceImpl implements CrawlerService{

	@Autowired
	private CrawlerRepository  crawRep;
	
	@Override
	public void start(int thread, int depth, boolean endpoint) {
		String crawlPath = "jd_book";
		JdBookCrawler crawler =new JdBookCrawler(crawlPath, true, crawRep);
		crawler.setResumable(endpoint);
		crawler.setThreads(thread);
		try {
			crawler.start(depth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
