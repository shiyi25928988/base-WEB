package crawler.base;

import java.util.Arrays;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicBoolean;

import crawler.base.exceptions.InvalidSeedException;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class CrawlerLauncher {

	private static AtomicBoolean isLaunched = new AtomicBoolean(false);

	public static void launch() {
		if (isLaunched.get())
			return;

	}

	public static void start(String...seeds) throws Exception {
		
		CrawlConfig config = CrawlerConfig.getConfig();

		PageFetcher pageFetcher = new PageFetcher(config);

		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		
	    robotstxtConfig.setEnabled(false);// enable the Robots protocols 
		
		RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);

		CrawlController controller = new CrawlerController(config, pageFetcher, robotstxtServer);

		if (Objects.isNull(seeds) || seeds.length == 0) {
			throw new InvalidSeedException();
		} else {
			Arrays.asList(seeds).stream().forEach(seed->{
				controller.addSeed(seed);
			});
		}

		new Thread(new MessageConsumer()).start();
		
		controller.start(Crawler.class, CrawlerConstants.NUMBER_OF_CRAWLERS);

	}

}