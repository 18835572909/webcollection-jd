package springboot.crawler.processor;

import java.util.Date;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.rocks.BreadthCrawler;
import cn.hutool.http.HttpUtil;
import springboot.crawler.pojo.JdBookDetail;
import springboot.crawler.rep.CrawlerRepository;

/**
 * @author renhuibo  2019-08-12 19:48:38
 * @Description
 */
public class JdBookCrawler extends BreadthCrawler{

	public static final String JD_SEARCH_BOOK_MAIN_URL="https://book.jd.com/booksort.html";
	
	public static final String JD_BOOK_LIST_MAIN = "https://list.jd.com/list.html?cat=CAT";
	
	public static final String JD_BOOK_LIST_PAGE_URL = "https://list.jd.com/list.html?cat=CAT&page=PAGE&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main";
	
	private CrawlerRepository crawRep;
	
	/**
	 * 构造器
	 * @param crawlPath
	 * @param autoParse
	 */
	public JdBookCrawler(String crawlPath, boolean autoParse, CrawlerRepository  crawRep) {
		super(crawlPath, autoParse);
		this.crawRep = crawRep;
		this.addSeed(new CrawlDatum(JdBookCrawler.JD_SEARCH_BOOK_MAIN_URL).meta("depth", 1));
		this.addRegex("https://list.jd.com/[0-9]+-[0-9]+-[0-9]+.html");
		this.addRegex("https://channel.jd.com/[0-9]+-[0-9]+.html");
		this.addRegex("https://item.jd.com/[0-9]+.html");
        addRegex("-.*\\.(jpg|png|gif).*");
        addRegex("-.*#.*");
	}

	@Override
	public void visit(Page page, CrawlDatums next) {
		// TODO Auto-generated method stub
		String url = page.url();
		String content = new String(page.content());
		
		//添加延迟： 太鸡儿快，没办法 *-*
		try {
			Thread.sleep(300);
		} catch (InterruptedException e2) {
			e2.printStackTrace();
		}
		if (page.code() == 301 || page.code() == 302) {
			next.addAndReturn(page.location()).meta(page.meta());
			return;
		}
		
		if(url.matches("https://list.jd.com/list.html\\?cat=.+")) {
			//页数：共267页 到第
			String pageNum = page.select("div#J_bottomPage").select("span.p-skip").select("em").first().text();
			pageNum = pageNum.substring(pageNum.indexOf("共")+1, pageNum.indexOf("页"));
			int pagenum = Integer.parseInt(pageNum);
			for(int i=2 ; i<=pagenum; i++) {
				next.add(url+"&page="+i+"&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main");
			}
		}
		
		if(page.matchUrl(".+jd.com/\\d+.html")){
			JdBookDetail bookDetail = new JdBookDetail();
			bookDetail.setJdBookId(CrawlerUtils.uuid());
			bookDetail.setJdBookOptime(new Date());
			String type = page.select("a[clstag='shangpin|keycount|product|mbNav-2']").text();
			bookDetail.setJdBookType1(type);
			String bookName = "";
			try{
				bookName = page.select("div#name").select("div.sku-name").first().text();
			}catch(NullPointerException e){
				
			}
			bookDetail.setJdBookName(bookName);
			Elements listE = page.select("ul#parameter2").select("li");
			for(Element e : listE){
				System.out.println("\t"+e.text());
				CrawlerUtils.putValue(e.text(), bookDetail);
			}
			crawRep.save(bookDetail);
		}
		
	}
	
	@AfterParse
    public void afterParse(Page page, CrawlDatums next) {
        //当前页面的depth为x，则从当前页面解析的后续任务的depth为x+1
        int depth = 1;
        //如果在添加种子时忘记添加depth信息，可以通过这种方式保证程序不出错
        try {
            depth = page.metaAsInt("depth");
        } catch (Exception ex) {

        }
        depth++;
        next.meta("depth", depth);
    }
}
