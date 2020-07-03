package custom.business;

import java.io.IOException;

import lego.ServiceBooter;

public class Main {
	public static void main(String... strings) {
		try {
			ServiceBooter.startOn(Main.class, new CustomModule());
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
