package base.crawler;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.parser.Parser;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * CrawlController wrapper
 * @author yshi
 *
 */
public class CrawlerController extends CrawlController {

	/**
	 * @param config
	 * @param pageFetcher
	 * @param parser
	 * @param robotstxtServer
	 * @throws Exception
	 */
	public CrawlerController(CrawlConfig config, PageFetcher pageFetcher, Parser parser,
			RobotstxtServer robotstxtServer) throws Exception {
		super(config, pageFetcher, parser, robotstxtServer);
	}
	
    /**
     * @param config
     * @param pageFetcher
     * @param robotstxtServer
     * @throws Exception
     */
    public CrawlerController(CrawlConfig config, PageFetcher pageFetcher, RobotstxtServer robotstxtServer) throws Exception {
    	this(config, pageFetcher, new Parser(config), robotstxtServer);
    }

}
