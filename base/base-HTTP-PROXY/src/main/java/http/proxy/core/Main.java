package http.proxy.core;

import java.io.IOException;

import http.proxy.core.annotation.PropertiesFile;
import http.proxy.core.booter.ServiceBooter;
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
			ServiceBooter.startFrom(http.proxy.core.Main.class);
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
	}
}
