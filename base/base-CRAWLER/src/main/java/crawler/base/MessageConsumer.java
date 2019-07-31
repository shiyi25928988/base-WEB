package crawler.base;

import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author yshi
 *
 */
public class MessageConsumer implements Runnable {

	Queue<CrawlResults> queue = QueueHolder.getQuene();

	private AtomicBoolean stop = new AtomicBoolean(false);

	public void run() {
		while (!stop.get()) {
			CrawlResults results = this.queue.poll();

			if (Objects.isNull(results)) {
				continue;
			}

			WriteFile.write(results.getUrl(), results.getText());
		}
	}

	public void stop() {
		stop.set(true);
	}
}
