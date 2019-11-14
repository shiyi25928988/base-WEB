package base.crawler.config;

import java.io.File;

public interface CrawlerConstants {
	
	public static final int NUMBER_OF_CRAWLERS = 1;
	
	public static final String CURRENT_PATH = System.getProperty("user.dir") + File.separator + "crawl" ; 
	
	public static final int MESSAGE_QUEUE_SIZE = 100;
}
