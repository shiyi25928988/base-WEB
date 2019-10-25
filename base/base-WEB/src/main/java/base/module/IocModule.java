package base.module;

import java.io.IOException;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import base.IOC.ClassHelper;
import base.rest.utils.RestService;
import base.rest.utils.RestServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class IocModule extends AbstractModule {

	private static Set<Class<?>> controllerClassSet;
	
	static {
		try {
			controllerClassSet = ClassHelper.getControllers("base");
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
	}

	/* (non-Javadoc)
	 * @see com.google.inject.AbstractModule#configure()
	 */
	@Override
	protected void configure() {
		bind(RestService.class).toProvider(RestServiceProvider.class);
	}

	/**
	 * To provide a RestService implement class
	 *
	 */
	public static class RestServiceProvider implements Provider<RestService> {
		/* 
		 * RestService is designed to process the classes which was annotated by the rest method
		 */
		@Override
		public RestService get() {
			return new RestServiceImpl(controllerClassSet);
		}
	}
}
