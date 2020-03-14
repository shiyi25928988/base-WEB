package lego.module;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.AbstractModule;
import com.google.inject.Provider;

import lego.ioc.ClassHelper;
import lego.ioc.PackageScanConfig;
import lego.ioc.RestApiService;
import lego.ioc.RestApiServiceImpl;
import lombok.extern.slf4j.Slf4j;

/**
 * @author yshi
 *
 */
@Slf4j
public class IocModule extends AbstractModule {

	private static Set<Class<?>> controllerClassSet = new HashSet<>();
	
	//private static List<String> scanPackageList = new 
	
	
	public IocModule() {
		try {
			controllerClassSet.addAll(ClassHelper.getControllers(IocModule.class.getPackageName().substring(0, IocModule.class.getPackageName().indexOf('.'))));
			PackageScanConfig.getPackageList().forEach(n->{
				try {
					controllerClassSet.addAll(ClassHelper.getControllers(n));
				} catch (ClassNotFoundException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			});
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
