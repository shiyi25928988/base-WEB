package base.crawler;

import com.google.inject.Guice;
import com.google.inject.Injector;

import base.crawler.guice.CrawlerModule;
import base.crawler.guice.CrawlerService;

/**
 * Entry class
 * 
 * @author yshi
 *
 */
public class Main {


	/**
	 * @param args
	 */
	public static void main(String... args) {
		Injector injector = Guice.createInjector(new CrawlerModule());
		CrawlerService service = injector.getInstance(CrawlerService.class);
		try {
			service.start(args);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
