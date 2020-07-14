package lego;

import java.io.IOException;

import lego.annotation.PropertiesFile;

/**
 * @author shiyi
 *
 */
@PropertiesFile(
		files = { 
					"application.properties" 
				}
	)
public class Main {

	public static void main(String... strings)  {
		try {
			//ServiceBooter.startOn(null);
			ServiceBooter.startOnJetty(lego.Main.class);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
