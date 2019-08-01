package crawler.base.test;

import crawler.base.CrawlerLauncher;

public class TestCrawler {
	
	public static void  main(String...strings) throws Exception {
		CrawlerLauncher.start("https://www.csdn.net/");
	}

}
