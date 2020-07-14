package lego;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;

import lego.annotation.PropertiesFile;
import lego.jetty.service.JettyBootService;
import lego.module.IocModule;
import lego.module.JettyModule;

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
		if (Objects.nonNull(mainClass))
			loadPropertiesFile(mainClass);
			IocModule.registScanPackage(mainClass);
		
		if (Objects.nonNull(modules) && modules.length > 0) {
			for(Module module : modules) {
				moduleList.add(module);
			}
		} 
		
		injector = Guice.createInjector(moduleList);
		JettyBootService service = injector.getInstance(JettyBootService.class);
		service.start();
	}
	
	private static void loadPropertiesFile(Class<?> mainClass) {
		PropertiesFile p = mainClass.getAnnotation(PropertiesFile.class);
		
		//Thread.currentThread().getContextClassLoader().getResourceAsStream("mq.properties")
	}

}
