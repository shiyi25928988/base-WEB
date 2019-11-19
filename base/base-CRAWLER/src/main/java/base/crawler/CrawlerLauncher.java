package base.crawler;

import java.util.Arrays;
import java.util.Objects;

import base.crawler.config.CrawlerConfig;
import base.crawler.config.CrawlerConstants;
import base.crawler.config.QueueMoniter;
import base.crawler.crawler.AbstractCrawler;
import base.crawler.exceptions.InvalidSeedException;
import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

/**
 * @author yshi
 *
 */
public class CrawlerLauncher {

	/**
	 * @param clazz
	 * @param seeds
	 * @throws Exception
	 */
	public static void start(Class<? extends AbstractCrawler> clazz, String...seeds) throws Exception {
		start(clazz, CrawlerConstants.NUMBER_OF_CRAWLERS, seeds);
	}

	/**
	 * @param clazz
	 * @param numOfCrawler
	 * @param seeds
	 * @throws Exception
	 */
	public static void start(Class<? extends AbstractCrawler> clazz, int numOfCrawler, String...seeds) throws Exception {
		
		
		
		CrawlConfig config = CrawlerConfig.getConfig();

		PageFetcher pageFetcher = new PageFetcher(config);
		
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		
		robotstxtConfig.setIgnoreUADiscrimination(true);
		
		robotstxtConfig.setUserAgentName("Googlebot");
		
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

		//new MessageConsumerGroup(1).start();
		
		new Thread(new MessageConsumer(), "consumer-thread").start();
		
		QueueMoniter.startMoniter();
		
		controller.start(clazz, numOfCrawler);

	}

}
