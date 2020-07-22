package base.crawler.guice;

import java.io.IOException;
import java.util.Objects;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provider;

import base.crawler.crawlerType.EveryThingCrawler;
import base.crawler.guice.service.CrawlerLauncher;
import base.crawler.guice.service.CrawlerLauncherService;
import base.crawler.guice.service.MessageConsumerGroup;
import base.crawler.guice.service.MessageConsumerGroupService;
import cache.base.module.CacheModule;
import db.base.module.DataSourceModule;
import db.base.service.NewsService;

/**
 * @author shiyi
 *
 */
public class CrawlerModule extends AbstractModule {
	
	/**
	 *
	 */
	@Override
	protected void configure() {
		try {
			System.getProperties().load(Thread.currentThread().getContextClassLoader().getResourceAsStream("application.properties"));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		install(new CacheModule());
		install(new DataSourceModule());
		bind(MessageConsumerGroupService.class).toProvider(MessageConsumerGroupServiceProvider.class);
		bind(CrawlerLauncherService.class).toProvider(CrawlerLauncherServiceProvider.class);
		bind(CrawlerService.class).toProvider(CrawlerServiceProvider.class);
	}
	
	/**
	 * @author shiyi
	 *
	 */
	private static class CrawlerLauncherServiceProvider implements Provider<CrawlerLauncherService> {

		@Inject
		MessageConsumerGroupService messageConsumerGroup;
		
		@Override
		public CrawlerLauncherService get() {
			Objects.requireNonNull(messageConsumerGroup);
			return new CrawlerLauncher(messageConsumerGroup);
		}
	}
	
	/**
	 * @author shiyi
	 *
	 */
	private static class CrawlerServiceProvider implements Provider<CrawlerService>{

		@Inject
		CrawlerLauncherService crawlerLauncher;
		
		@Override
		public CrawlerService get() {
			Objects.requireNonNull(crawlerLauncher);
			return new CrawlerServiceImpl(crawlerLauncher, EveryThingCrawler.class);
		}
		
	}
	
	/**
	 * @author shiyi
	 *
	 */
	private static class MessageConsumerGroupServiceProvider implements Provider<MessageConsumerGroupService>{
		@Inject
		NewsService newsService;
		@Override
		public MessageConsumerGroupService get() {
			return new MessageConsumerGroup(100, newsService);
		}
	}
}
