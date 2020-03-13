package lego.module;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

/**
 * @author yshi
 *
 */
public class EventBusModule extends AbstractModule{

	/**
	 *
	 */
	@Override
	protected void configure() {
		bind(EventBus.class).toProvider(EventBusProvider.class).in(Singleton.class);
	}
	
	/**
	 * @author yshi
	 *
	 */
	public static class EventBusProvider implements Provider<EventBus>{
		ExecutorService executor = Executors.newSingleThreadExecutor();
		/**
		 *
		 */
		@Override
		public EventBus get() {
			return new AsyncEventBus(executor);
		}
	}
	
	
}
