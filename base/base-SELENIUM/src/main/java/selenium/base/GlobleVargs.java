package selenium.base;

import java.io.File;

/**
 * @author shiyi
 *
 */
public final class GlobleVargs {
	
	private static final String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "drivers" + File.separator;// + "chrome"
	
	public static final String chromeDriverPropertyKey = "webdriver.chrome.driver";
	public static final String chromeDriverPath_v79 = driverPath + "chrome" + File.separator + "79" + File.separator + "chromedriver.exe";
	public static final String chromeDriverPath_v80 = driverPath + "chrome" + File.separator + "80" + File.separator + "chromedriver.exe";
	
	public static final String firefoxDriverPropertyKey = "webdriver.gecko.driver";
	public static final String firefoxDriverPath = driverPath + "firefox" + File.separator + "0.26.0" + File.separator + "geckodriver.exe";
	
	public static String browserType;
	public static String browserVersion;
	public static String scriptFile;
}
