package base.crawler.guice.service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.google.inject.Inject;

import base.crawler.CrawlResults;
import base.crawler.config.QueueHolder;
import base.crawler.exceptions.ConsumerThreadExceptionHandler;
import base.crawler.utils.DbWriter;
import db.base.service.NewsService;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class MessageConsumerGroup implements MessageConsumerGroupService {

	private int messageConsumerCount = 4;

	private List<MessageConsumer> consumers = new ArrayList<MessageConsumer>();

	private ExecutorService executor;

	private DbWriter dbWriter;

	/**
	 * @param messageConsumerCount
	 */
//	public MessageConsumerGroup(int messageConsumerCount, NewsService newService) {
//		
//		if(messageConsumerCount < 1) {
//			this.messageConsumerCount = 1;
//		} else
//		this.messageConsumerCount = messageConsumerCount;
//		
//		for(int i = 0 ; i < this.messageConsumerCount ; i++) {
//			consumers.add(new MessageConsumer(newService));
//		}
//		
//		executor = Executors.newFixedThreadPool(64 ,new ThreadFactory() {
//			
//			@Override
//			public Thread newThread(Runnable r) {
//				
//				Thread thread = new Thread(new ThreadGroup("CONSUMER-THREAD"), r);
//				thread.setName("consumer");
//				thread.setUncaughtExceptionHandler(new ConsumerThreadExceptionHandler(executor));
//				return thread;
//			}
//			
//		});
//	}

	public MessageConsumerGroup(int messageConsumerCount, NewsService newService) {

		Objects.requireNonNull(newService, "MessageConsumerGroup -> newService is null ");

		this.dbWriter = new DbWriter(newService);

		executor = Executors.newFixedThreadPool(64);
	}

	/**
	 * @throws NullPointerException
	 */
//	public void start() throws NullPointerException {
//
//		if (Objects.isNull(consumers))
//			throw new NullPointerException();
//		if (Objects.isNull(executor))
//			throw new NullPointerException();
//
//		consumers.stream().forEach(consumer -> {
//			executor.submit(consumer);
//		});
//	}

	public void start() throws NullPointerException {

		Objects.requireNonNull(dbWriter);
		Objects.requireNonNull(executor);
		
		Runnable runnable = new Runnable() {

			@Override
			public void run() {
				for(;;) {
					if(QueueHolder.getQuene().size()>4)
					executor.submit(new WriteDBTask());
				}
				
			}
			
		};

		new Thread(runnable).start();
		
	}

	class WriteDBTask implements Callable<Integer> {

		@Override
		public Integer call() throws Exception {

			for (;;) {
				log.info("MessageConsumerGroup running...");

				CrawlResults crawlResults = QueueHolder.getQuene().take();
				if (Objects.isNull(crawlResults)) {
					continue;
				}
				dbWriter.write(crawlResults);
			}
			
		}

	}

	/**
	 * 
	 */
	public void sopt() {
//		consumers.parallelStream().forEach(consumer -> {
//			consumer.stop();
//		});
	}
}
