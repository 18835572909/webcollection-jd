package springboot.crawler.processor;

import java.util.UUID;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Element;

import springboot.crawler.pojo.JdBookDetail;

/**   
 * @author: renhuibo
 * @date:   2019年8月9日 下午2:30:04  
 * @Description:  
 */
public class CrawlerUtils {

	
	public static String uuid() {
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static String getValue(Element element,String id){
		element = element.getElementById(id);
		if(element == null)
			return "";
		else {
			element = element.getElementsByClass("book-detail-content").first();
			if(element == null)
				return "";
			else 
				return element.text();
		}
	}
	
	/**
	 * @author: renhuibo
	 * @date:   2019年8月9日 下午2:45:50  
	 * @Description: 数据转对象
	 */
	public static JdBookDetail putValue(String content,JdBookDetail bookDetail){
		if(StringUtils.isBlank(content))
			return bookDetail;
		if(content.contains("：")){
			String[] keyValue = content.split("：");
			String key = keyValue[0] == null ? "" : keyValue[0];
			String value = keyValue[1] == null ? "" : keyValue[1];
			switch (key) {
			case "出版社":
				bookDetail.setJdBookPress(value);
				break;
			case "ISBN":
				bookDetail.setJdBookIsbn(value);
				break;
			case "版次":
				bookDetail.setJdBookVersion(value);
				break;
			case "商品编码":
				bookDetail.setJdBookCode(value);
				break;
			case "品牌":
				bookDetail.setJdBookBrand(value);
				break;
			case "包装":
				bookDetail.setJdBookPackaging(value);
				break;
			case "开本":
				bookDetail.setJdBookSize(value);
				break;
			case "出版时间":
				bookDetail.setJdBookTimep(value);
				break;
			case "用纸":
				bookDetail.setJdBookPaper(value);
				break;
			case "页数":
				bookDetail.setJdBookPage(value);
				break;
			case "套装数量":
				break;
			case "字数":
				bookDetail.setJdBookWordnum(value);
				break;
			case "正文语种":
				break;
			case "附件":
				break;
			case "附件数量":
				break;
			default:
				break;
			}
		}
		return bookDetail;
	}

}
