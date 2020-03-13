package lego.module;

import java.io.IOException;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import lego.ioc.ClassHelper;
import lego.ioc.RestApiService;
import lego.ioc.RestApiServiceImpl;
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
			/*
			 * To collect all the classes from the given DIR
			 * */
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
		bind(RestApiService.class).toProvider(RestServiceProvider.class);
	}

	/**
	 * To provide a RestService implement class
	 *
	 */
	public static class RestServiceProvider implements Provider<RestApiService> {
		/* 
		 * RestService is designed to process the classes which was annotated by the rest method
		 */
		@Override
		public RestApiService get() {
			return new RestApiServiceImpl(controllerClassSet);
		}
	}
}
