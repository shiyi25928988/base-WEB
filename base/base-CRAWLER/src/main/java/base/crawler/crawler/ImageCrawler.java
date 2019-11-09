package base.crawler.crawler;

import java.util.regex.Pattern;

/**
 * @author yshi
 *
 */
public class ImageCrawler extends AbstractCrawler {

	private static final Pattern imgPatterns = Pattern.compile(".*(\\.(bmp|gif|jpe?g|png|tiff?))$");
	
	/**
	 *
	 */
	@Override
	protected Pattern getPattern() {
		return imgPatterns;
	}
}
