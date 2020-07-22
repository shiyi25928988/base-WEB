package base.crawler.guice.service;

import java.util.Arrays;
import java.util.Objects;

import com.google.inject.Inject;

import base.crawler.CrawlerController;
import base.crawler.cli.GlobalVars;
import base.crawler.config.CrawlerConfig;
import base.crawler.config.QueueMoniter;
import base.crawler.crawlerType.AbstractCrawler;
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
public class CrawlerLauncher implements CrawlerLauncherService{
	
	private MessageConsumerGroupService messageConsumerGroup;
	
	public CrawlerLauncher(MessageConsumerGroupService messageConsumerGroup) {
		this.messageConsumerGroup = messageConsumerGroup;
	}

	/**
	 * @param clazz
	 * @param seeds
	 * @throws Exception
	 */
	@Override
	public void start(Class<? extends AbstractCrawler> clazz, String...seeds) throws Exception {
		start(clazz, GlobalVars.crawlerNumber, seeds);
	}

	/**
	 * @param clazz
	 * @param numOfCrawler
	 * @param seeds
	 * @throws Exception
	 */
	@Override
	public void start(Class<? extends AbstractCrawler> clazz, int numOfCrawler, String...seeds) throws Exception {
		
		CrawlConfig config = CrawlerConfig.getConfig();

		PageFetcher pageFetcher = new PageFetcher(config);
		
		RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
		
		robotstxtConfig.setIgnoreUADiscrimination(true);
		
		robotstxtConfig.setUserAgentName("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/84.0.4147.89 Safari/537.36");//set user agent
		
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

		messageConsumerGroup.start();
		
		QueueMoniter.startMoniter();
		
		controller.start(clazz, numOfCrawler);

	}

}
