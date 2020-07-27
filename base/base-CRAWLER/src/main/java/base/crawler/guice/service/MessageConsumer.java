package base.crawler.guice.service;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import base.crawler.CrawlResults;
import base.crawler.config.QueueHolder;
import base.crawler.utils.DbWriter;
import db.base.service.NewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class MessageConsumer implements Runnable {

	BlockingQueue<CrawlResults> queue = QueueHolder.getQuene();

	private AtomicBoolean stop = new AtomicBoolean(false);
	
	private static NewsService staticNewsService;
	
	private DbWriter dbWriter;
	
	public MessageConsumer(NewsService newService) {
		Objects.requireNonNull(newService, "NewsService is null");
		staticNewsService = newService;
		this.dbWriter = new DbWriter(newService);
	}
	
	public static NewsService getNewsService() {
		return staticNewsService;
	} 

	/**
	 *
	 */
	@Override
	public void run() {
		
		CrawlResults results;
		
		for(;;){
			try {
				results = this.queue.take();
			} catch (InterruptedException e) {
				e.printStackTrace();
				log.error(e.toString());
				continue;
			}
			try {
				dbWriter.write(results);
			} catch (Exception e) {
				log.info(e.getLocalizedMessage());
			}
		}
	}

	/**
	 * Stop the consumer thread.
	 */
	public void stop() {
		stop.set(true);
	}
}
