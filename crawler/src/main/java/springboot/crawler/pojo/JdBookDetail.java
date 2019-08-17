package springboot.crawler.pojo;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * @author renhuibo  2019-08-12 22:58:12
 * @Description
 */
@Getter
@Setter
@Entity
@Table(name="jd_book_detail")
public class JdBookDetail {
	@Id
	private String jdBookId;
	@Basic
	private Date jdBookOptime;
	@Basic
	private String jdBookType1;
	@Basic
	private String jdBookName;
	@Basic
	private String jdBookPress;
	@Basic
	private String jdBookIsbn;
	@Basic
	private String jdBookVersion;
	@Basic
	private String jdBookCode;
	@Basic
	private String jdBookBrand;
	@Basic
	private String jdBookPackaging;
	@Basic
	private String jdBookSize;
	@Basic
	private String jdBookTimep;
	@Basic
	private String jdBookPaper;
	@Basic
	private String jdBookPage;
	@Basic
	private String jdBookWordnum;
}
