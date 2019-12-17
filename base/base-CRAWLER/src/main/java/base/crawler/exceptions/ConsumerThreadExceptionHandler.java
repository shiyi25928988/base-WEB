package base.crawler.exceptions;

import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ExecutorService;

import base.crawler.MessageConsumer;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class ConsumerThreadExceptionHandler implements UncaughtExceptionHandler{
	
	private ExecutorService executorService;

	public ConsumerThreadExceptionHandler(ExecutorService executorService){
		this.executorService = executorService;
	}

	@Override
	public void uncaughtException(Thread t, Throwable e) {
		
		log.error(e.getMessage());
		
		executorService.submit(new MessageConsumer());
		
	}

}
