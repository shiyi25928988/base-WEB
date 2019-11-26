package base.crawler.guice;

import com.google.inject.AbstractModule;

import cache.base.module.CacheModule;

public class CrawlerModule extends AbstractModule {
	
	@Override
	protected void configure() {
		install(new CacheModule());
		bind(CrawlerService.class).to(CrawlerServiceImpl.class);
	}
}
