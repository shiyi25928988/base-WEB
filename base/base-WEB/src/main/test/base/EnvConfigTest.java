package base;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class EnvConfigTest {

	public static void main(String...strings) {
		
		InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("env.properties");
		Properties properties = new Properties();
		try {
			properties.load(is);
			
			System.out.println(properties.get("test"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
