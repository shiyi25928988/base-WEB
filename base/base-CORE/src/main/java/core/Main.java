package core;

import java.io.IOException;

import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;
import lombok.extern.slf4j.Slf4j;

/**
 * @author shiyi
 *
 */
@PropertiesFile(files = { "application.properties" })
@Slf4j
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
