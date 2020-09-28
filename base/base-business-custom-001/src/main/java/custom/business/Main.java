package custom.business;

import java.io.IOException;

import core.annotation.PropertiesFile;
import core.booter.ServiceBooter;
import lombok.extern.slf4j.Slf4j;

@PropertiesFile(files = { "application.properties" })
@Slf4j
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
