package dl;

import java.io.IOException;

import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;
import dl.module.DownLoadModule;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertiesFile(files = { "application.properties" })
public class Main {
	public static void main(String... strings) {
		try {
			ServiceBooter.startFrom(dl.Main.class, new DownLoadModule());
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
	}
}
