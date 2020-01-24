package selenium.base.cli;

import java.io.File;

/**
 * @author shiyi
 *
 */
public final class CliGlobleVargs {
	
	public static final String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "drivers" + File.separator + "chrome"
			+ File.separator + "chromedriver.exe";
	public static String browserType;
	public static String browserVersion;
}
