package base.crawler;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

import base.crawler.config.QueueHolder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class MessageConsumer implements Runnable {

	Queue<CrawlResults> queue = QueueHolder.getQuene();

	private AtomicBoolean stop = new AtomicBoolean(false);

	/**
	 *
	 */
	public void run() {
		while (!stop.get()) {
			
			CrawlResults results = this.queue.poll();
			if (Objects.isNull(results)) {
				continue;
			}
			log.error("============================================");
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
