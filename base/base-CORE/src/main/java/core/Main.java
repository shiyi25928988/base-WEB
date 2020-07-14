package core;

import java.io.IOException;

import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;

/**
 * @author shiyi
 *
 */
@PropertiesFile(files = { "application.properties" })
public class Main {

	public static void main(String... strings) {
		try {
			// ServiceBooter.startOn(null);
			ServiceBooter.startOnJetty(core.Main.class);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
