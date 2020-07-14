package core.booter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import core.annotation.PropertiesFile;
import core.module.IocModule;
import core.module.JettyModule;
import core.properties.CoreProperties;
import core.service.jetty.JettyBootService;



/**
 * @author
 *
 */
public class ServiceBooter {

	private static List<Module> moduleList = new ArrayList<>();
	static {
		moduleList.add(new JettyModule());
	}

	public static void startOnJetty(Class<?> mainClass, Module... modules) throws ClassNotFoundException, IOException {
		Injector injector;
		if (Objects.nonNull(mainClass)) {
			loadPropertiesFile(mainClass);
			IocModule.registScanPackage(mainClass);
		}
		
		if (Objects.nonNull(modules) && modules.length > 0) {
			for(Module module : modules) {
				moduleList.add(module);
			}
		} 
		
		injector = Guice.createInjector(moduleList);
		JettyBootService service = injector.getInstance(JettyBootService.class);
		service.start();
	}
	
	/**
	 * @param mainClass
	 */
	private static void loadPropertiesFile(Class<?> mainClass) {
		PropertiesFile propertiesFile = mainClass.getAnnotation(PropertiesFile.class);
		
		if(Objects.isNull(propertiesFile)) {
			return;
		}
		
		String[] fileName = propertiesFile.files();
		if(fileName.length <= 0) {
			return;
		}
		
		Arrays.asList(fileName).forEach(pf -> {
			CoreProperties.setProperties(pf);
		});
		
	}

}
