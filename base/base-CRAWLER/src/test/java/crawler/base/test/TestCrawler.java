package crawler.base.test;

import base.crawler.CrawlerLauncher;
import base.crawler.TextCrawler;

public class TestCrawler {
	
	public static void  main(String...strings) throws Exception {
		CrawlerLauncher.start(TextCrawler.class, "http://ifeve.com/");
		CrawlerLauncher.start(TextCrawler.class, "http://ifeve.com/");
	}

}
