package core.module;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Module;

public class ModuleRegister {
	
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

}
