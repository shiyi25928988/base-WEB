package base.crawler.guice.service;

import java.io.UnsupportedEncodingException;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import com.google.inject.Inject;

import base.crawler.CrawlResults;
import base.crawler.config.QueueHolder;
import base.crawler.utils.DbWriter;
import base.crawler.utils.FileWriter;
import db.base.entity.NewsEntity;
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
	
	private NewsService newService;
	
	private static NewsService staticNewsService;
	
	private DbWriter dbWriter;
	
	public MessageConsumer(NewsService newService) {
		Objects.requireNonNull(newService, "NewsService is null");
		this.newService = newService;
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
		
//		NewsEntity newsEntity = new NewsEntity();
//		newsEntity.setContent("test");
//		newService.insertNews(newsEntity);
		
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
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//FileWriter.writeResult(results);
		}
	}

	/**
	 * Stop the consumer thread.
	 */
	public void stop() {
		stop.set(true);
	}
}
