package base.module;

import java.io.IOException;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import base.IOC.ClassHelper;
import base.annotation.Controller;
import base.rest.RestService;
import base.rest.RestServiceImpl;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class RESTfulModule extends AbstractModule {

	private static Set<Class<?>> controllerClassSet;
	static {
		try {
			controllerClassSet = getControllers("base");
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
			System.exit(1);
		}
	}

	@Override
	protected void configure() {
		bind(RestService.class).toProvider(RestServiceProvider.class);
	}

	public static class RestServiceProvider implements Provider<RestService> {
		@Override
		public RestService get() {
			return new RestServiceImpl(controllerClassSet);
		}
	}

	private static Set<Class<?>> getControllers(String scanPackageName) throws ClassNotFoundException, IOException {
		Set<Class<?>> set = ClassHelper.getAnnotationClass(ClassHelper.getClassSet(scanPackageName), Controller.class);
		set.forEach(clazz -> log.info(clazz.getCanonicalName()));
		return set;
	}
	
}
