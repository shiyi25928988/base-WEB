package base.crawler.crawlerType;

import java.util.regex.Pattern;

import edu.uci.ics.crawler4j.crawler.Page;
import edu.uci.ics.crawler4j.url.WebURL;

public class EveryThingCrawler extends AbstractCrawler{

	@Override
	protected Pattern getPattern() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public boolean shouldVisit(Page referringPage, WebURL url) {
		return true;
	}

}
