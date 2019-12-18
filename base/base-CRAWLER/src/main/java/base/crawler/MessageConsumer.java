package base.crawler;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;

import base.crawler.config.QueueHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class MessageConsumer implements Runnable {

	BlockingQueue<CrawlResults> queue = QueueHolder.getQuene();

	private AtomicBoolean stop = new AtomicBoolean(false);

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
			WriteFile.writeResult(results);
		}
	}

	/**
	 * Stop the consumer thread.
	 */
	public void stop() {
		stop.set(true);
	}
}
