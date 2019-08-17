package springboot.crawler;

import junit.framework.TestCase;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase{
	
	public static final String JD_BOOK_LIST_MAIN = "https://list.jd.com/list.html?cat=CAT";
	
	public static final String JD_BOOK_LIST_PAGE_URL = "https://list.jd.com/list.html?cat=CAT&page=PAGE&sort=sort_rank_asc&trans=1&JL=6_0_0#J_main";
	
	public static void main(String[] args) {
		String url= "https://list.jd.com/1713-3258-3299.html";
		String url1 = "https://channel.jd.com/1713-3258.html";
		
		String reg = "https://list.jd.com/[0-9]+-[0-9]+-[0-9]+.html";
		String reg2 = "https://channel.jd.com/[0-9]+-[0-9]+.html";
		System.out.println(url.matches(reg));
		System.out.println(url1.matches(reg2));
		
		String ss = "456-456-456.html";
		System.out.println(ss.matches("[0-9]+-[0-9]+-[0-9]+.html"));
		
		String s = "https://item.jd.com/11757834.html";
		System.out.println(s.matches("https://item.jd.com/[0-9]+.html"));
		
		System.out.println(url1.substring(23,url1.length()-5).replaceAll("-", ","));
		
		System.out.println("https://list.jd.com/".length());
		System.out.println(url.substring(20,url.length()-5));
		
		
		System.out.println(JD_BOOK_LIST_MAIN.replaceAll("CAT", "4561,4562"));
		System.out.println(JD_BOOK_LIST_PAGE_URL.replaceAll("CAT", "4561,4562").replaceAll("PAGE", "1"));
		
		String re = "https://list.jd.com/list.html\\?cat=.+";
		System.out.println("https://list.jd.com/list.html?cat=1713,14669,14674&tid=14674".matches(re));
		
		String d = "共267页";
		System.out.println(d.substring(d.indexOf("共")+1, d.indexOf("页")));
	}
	
}
