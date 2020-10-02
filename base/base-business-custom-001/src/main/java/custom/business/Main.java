package custom.business;

import java.io.IOException;

import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;

@PropertiesFile(files = { "application.properties" })
public class Main {
	public static void main(String... strings) {
		try {
			ServiceBooter.startFrom(Main.class, new CustomModule());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
