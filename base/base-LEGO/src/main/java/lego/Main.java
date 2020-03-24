package lego;

import java.io.IOException;

/**
 * @author shiyi
 *
 */
public class Main {

	public static void main(String... strings)  {
		try {
			ServiceBooter.startOn(null);
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
