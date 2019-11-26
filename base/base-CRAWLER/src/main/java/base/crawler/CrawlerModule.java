package base.crawler;

import com.google.inject.AbstractModule;

import base.crawler.guice.CrawlerService;
import base.crawler.guice.CrawlerServiceImpl;

public class CrawlerModule extends AbstractModule {
	
	@Override
	protected void configure() {
		bind(CrawlerService.class).to(CrawlerServiceImpl.class);
	}
}
