package crawler.base;

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
	public String text;
}
