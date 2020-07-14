package core.properties;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

import com.google.common.base.Strings;

/**
 * @author shiyi
 *
 */
public class CoreProperties {
	
	private static Properties properties = new Properties();
	private static Map<String, String> propertiesFileRegister = new ConcurrentHashMap<>();

	public static void setProperties(String propertiesFileName) {
		propertiesFileRegister.put(propertiesFileName, propertiesFileName);
		try {
			properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream(propertiesFileName));
		} catch (IOException e) {
			e.printStackTrace();
			propertiesFileRegister.remove(propertiesFileName);
		}
	}
	
	/**
	 * @param key
	 * @return
	 */
	public static String getProperties(String key) {
		return getProperties(key, "");
	}
	
	/**
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	public static String getProperties(String key, String defaultValue) {
		
		if(CoreProperties.properties.containsKey(key)) {
			String value = CoreProperties.properties.getProperty(key);
			if(Strings.isNullOrEmpty(value)) {
				return defaultValue;
			}else {
				return value;
			}
		}else {
			return defaultValue;
		}
		
	}
	
	private class DynamicLoader implements Runnable{
		
		DynamicLoader(){
			
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
		}
		
	}
}
