package base.crawler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yshi
 *
 */
@Getter
@Setter
@AllArgsConstructor
public class CrawlResults {
	public String url;
	public byte[] content;
	public String extension;
	public ResultType type;
	
	public CrawlResults() {}
	
	public enum ResultType{
		Html,
		Text,
		Binary
	}
}
