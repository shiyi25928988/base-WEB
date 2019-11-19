package base.crawler.config;

import java.util.Queue;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author yshi
 *
 */
public class QueueMoniter implements Runnable{

	private static ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
	
	@SuppressWarnings("rawtypes")
	private Queue q = QueueHolder.getQuene();
	
	private static QueueMoniter moniter = new QueueMoniter();
	
	public static void startMoniter() {
		scheduler.scheduleAtFixedRate(moniter, 1_000L, 1_000L, TimeUnit.MILLISECONDS);
	}

	@Override
	public void run() {
		int l = q.size();
		
		for(int i = 0; i < l ; i++) {
			System.out.print("=");
		}
		System.out.println(l);
	}
	
	
}
