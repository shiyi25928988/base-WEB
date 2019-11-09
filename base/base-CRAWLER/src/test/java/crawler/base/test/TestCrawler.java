package crawler.base.test;

import base.crawler.CrawlerLauncher;
import base.crawler.crawler.EveryThingCrawler;
import base.crawler.crawler.ImageCrawler;
import base.crawler.crawler.TextCrawler;

public class TestCrawler {
	
	public static void  main(String...strings) throws Exception {
		CrawlerLauncher.start(EveryThingCrawler.class, "https://tineye.com/technology");
	}

}
