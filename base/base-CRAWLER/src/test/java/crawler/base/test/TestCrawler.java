package crawler.base.test;

import crawler.base.CrawlerLauncher;
import crawler.base.TextCrawler;

public class TestCrawler {
	
	public static void  main(String...strings) throws Exception {
		CrawlerLauncher.start(TextCrawler.class, "http://ifeve.com/");
		CrawlerLauncher.start(TextCrawler.class, "http://ifeve.com/");
	}

}
