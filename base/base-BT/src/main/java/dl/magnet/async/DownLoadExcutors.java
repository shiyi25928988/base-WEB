package dl.magnet.async;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class DownLoadExcutors {
	
	private static Executor e = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors() * 2);

	public static void submit(Runnable task) {
		e.execute(task);
	}
}
