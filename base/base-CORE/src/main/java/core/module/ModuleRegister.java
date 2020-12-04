package core.module;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.Stage;

public class ModuleRegister {
	
	private static Injector injector = null;
	
	private static List<Module>  MODULE_LIST = new ArrayList<>();
	
	public static int register(Module module) {
		MODULE_LIST.add(module);
		return MODULE_LIST.size();
	}
	
	public static Module[] getModulesAsArray() {
		
		Module[] modules = new Module[MODULE_LIST.size()];
		for(int i = 0; i < MODULE_LIST.size() ; i++) {
			modules[i] = MODULE_LIST.get(i);
		}
		
		return modules;
	}
	
	public static List<Module> getModulesAsList() {
		return MODULE_LIST;
	}
	
	public static Injector getInjector() {
		
		if(Objects.isNull(injector)) {
			injector = Guice.createInjector(Stage.DEVELOPMENT, getModulesAsList());
		}
		
		return injector;
	}

}
