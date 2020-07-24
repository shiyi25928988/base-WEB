package base.crawler.crawlerType;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@Slf4j
public class DefaultCrawler extends AbstractCrawler{

	@Override
	protected Pattern getPattern() {
		return null;
	}
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		return true;
	}
	
	@Override
	public void visit(Page page) {
		log.info("visit : " + page.getWebURL().getURL());
		log.info("by : " + this.getClass().getCanonicalName());
	}
}
