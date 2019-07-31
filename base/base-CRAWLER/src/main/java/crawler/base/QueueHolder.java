package crawler.base;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class QueueHolder {

	private static Queue<CrawlResults> queue = new ConcurrentLinkedQueue<CrawlResults>();

	public static Queue<CrawlResults> getQuene() {
		return queue;
	}
}
