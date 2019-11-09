package base.crawler;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * @author yshi
 *
 */
public class MessageConsumerGroup {
	
	private int messageConsumerCount = 4;
	
	private List<MessageConsumer> consumers = new ArrayList<MessageConsumer>(); 
	
	private ExecutorService executor;
	
	/**
	 * 
	 */
	public MessageConsumerGroup() {
		this(4);
	}

	/**
	 * @param messageConsumerCount
	 */
	public MessageConsumerGroup(int messageConsumerCount) {
		if(messageConsumerCount < 1) {
			this.messageConsumerCount = 1;
		} else
		this.messageConsumerCount = messageConsumerCount;
		
		for(int i = 0 ; i < this.messageConsumerCount ; i++) {
			consumers.add(new MessageConsumer());
		}
		
		executor = Executors.newFixedThreadPool(this.messageConsumerCount, new ThreadFactory() {
			@Override
			public Thread newThread(Runnable r) {
				return new Thread(new ThreadGroup("CONSUMER-THREAD"), r);
			}
			
		});
	}
	
	/**
	 * @throws NullPointerException
	 */
	public void start() throws NullPointerException{
		
		if(Objects.isNull(consumers)) throw new NullPointerException();
		if(Objects.isNull(executor)) throw new NullPointerException();
		
		consumers.stream().forEach(consumer -> {
			executor.submit(consumer);
		});
	}
	
	/**
	 * 
	 */
	public void sopt() {
		consumers.parallelStream().forEach(consumer -> {
			consumer.stop();
		});
	}
}
