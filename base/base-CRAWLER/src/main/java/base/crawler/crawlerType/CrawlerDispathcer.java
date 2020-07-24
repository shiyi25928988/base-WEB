package base.crawler.crawlerType;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;

import base.crawler.config.WebSiteType;
import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

/**
 * @author shiyi
 *
 */
public class CrawlerDispathcer extends AbstractCrawler{
	
	private static Map<String, AbstractCrawler> crawlerMapper = new ConcurrentHashMap<>();

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
		String domain = page.getWebURL().getDomain();
		if(crawlerMapper.containsKey(domain)) {
			AbstractCrawler crawler = crawlerMapper.get(domain);
			crawler.visit(page);
		}else {
			AbstractCrawler crawler = WebSiteType.getWebSiteCrawler(WebSiteType.getWebSiteType(domain));
			crawlerMapper.put(domain, crawler);
			crawler.visit(page);
		}
	}
}
