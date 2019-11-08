package base.crawler.config;

public interface CrawlerConstants {
	
	public static final int NUMBER_OF_CRAWLERS = 4 * Runtime.getRuntime().availableProcessors();
	
	public static final String CURRENT_PATH = System.getProperty("user.dir"); 
	
	public static final int MESSAGE_QUEUE_SIZE = 100;
}
