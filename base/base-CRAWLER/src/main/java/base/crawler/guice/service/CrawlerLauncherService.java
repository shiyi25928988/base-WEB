package base.crawler.guice.service;

import com.google.inject.ImplementedBy;

import base.crawler.crawlerType.AbstractCrawler;

public interface CrawlerLauncherService {
	public void start(Class<? extends AbstractCrawler> clazz, String...seeds) throws Exception;
	public void start(Class<? extends AbstractCrawler> clazz, int numOfCrawler, String...seeds) throws Exception;
}
