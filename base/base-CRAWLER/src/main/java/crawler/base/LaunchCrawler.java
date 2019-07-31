package crawler.base;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class LaunchCrawler {

	private static AtomicBoolean isLaunched = new AtomicBoolean(false);

	public static void launch() {
		if (isLaunched.get())
			return;

	}

	public static void start(String seed) throws Exception {
		CrawlConfig config = CrawlerConfig.getConfig();

		PageFetcher pageFetcher = new PageFetcher(config);

		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

		CrawlController controller;

		controller = new CrawlerController(config, pageFetcher, robotstxtServer);

		if (Objects.isNull(seed)) {
			throw new InvalidSeedException();
		} else {
			controller.addSeed(seed);
		}

		controller.start(Crawler.class, CrawlerConstants.NUMBER_OF_CRAWLERS);

	}

}
