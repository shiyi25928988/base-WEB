package base.crawler.config;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import base.crawler.CrawlResults;

/**
 * @author yshi
 *
 */
public class QueueHolder {

	private static Queue<CrawlResults> queue = new ConcurrentLinkedQueue<CrawlResults>();

	public static Queue<CrawlResults> getQuene() {
		return queue;
	}
}
