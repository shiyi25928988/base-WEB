package crawler.base;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerController extends CrawlController {

	public CrawlerController(CrawlConfig config, PageFetcher pageFetcher, Parser parser,
			RobotstxtServer robotstxtServer) throws Exception {
		super(config, pageFetcher, parser, robotstxtServer);
	}
	
    public CrawlerController(CrawlConfig config, PageFetcher pageFetcher, RobotstxtServer robotstxtServer) throws Exception {
    	this(config, pageFetcher, new Parser(config), robotstxtServer);
    }

}
