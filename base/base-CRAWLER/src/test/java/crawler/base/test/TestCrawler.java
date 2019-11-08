package crawler.base.test;

import base.crawler.CrawlerLauncher;
import base.crawler.ImageCrawler;
import base.crawler.TextCrawler;

public class TestCrawler {
	
	public static void  main(String...strings) throws Exception {
		CrawlerLauncher.start(ImageCrawler.class, "https://juejin.im/");
	}

}
