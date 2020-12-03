package base.dl;

import java.io.IOException;

import base.dl.module.DownLoadModule;
import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@PropertiesFile(files = { "application.properties" })
public class Main {
	public static void main(String... strings) {
		try {
			ServiceBooter.startFrom(base.dl.Main.class, new DownLoadModule());
		} catch (ClassNotFoundException | IOException e) {
			log.error(e.getMessage());
		}
	}
}
