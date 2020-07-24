package base.crawler;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author yshi
 *
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CrawlResults {
	public String url;
	public String title;
	public String imageUrl;
	public String fileName;
	public byte[] content;
	public String extension;
	public ResultType type;
	public String rootFolder;
	public String protocol;
	public String charSet;
	public String source;
	public Date releaseDate;
	public String keyWord;
	public String contentType;
	
	public enum ResultType{
		Html,
		Text,
		Binary
	}
}
