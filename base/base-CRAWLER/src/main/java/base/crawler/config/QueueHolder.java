package base.crawler.config;

import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import base.crawler.CrawlResults;

/**
 * @author yshi
 *
 */
public class QueueHolder {

	private static BlockingQueue<CrawlResults> queue = new LinkedBlockingQueue<CrawlResults>();

	/**
	 * @return queue instance.
	 */
	public static BlockingQueue<CrawlResults> getQuene() {
		return queue;
	}
}
