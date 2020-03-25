package lego.module;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import lego.ioc.ClassHelper;
import lego.ioc.service.RestApiService;
import lego.ioc.service.RestApiServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class IocModule extends AbstractModule {

	private static Set<Class<?>> controllerClassSet = new HashSet<>();
	
	public IocModule() {
		try {
			controllerClassSet.addAll(ClassHelper.getControllers(IocModule.class.getPackageName().substring(0, IocModule.class.getPackageName().indexOf('.'))));
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
	
	public static void registScanPackage(Class<?> clazz) throws ClassNotFoundException, IOException {
		registScanPackage(clazz.getPackage());
	}
	
	public static void registScanPackage(Package pack) throws ClassNotFoundException, IOException {
		controllerClassSet.addAll(ClassHelper.getControllers(pack.getName()));
	}
}
