package selenium.base.browser;

import org.openqa.selenium.WebDriver;

/**
 * @author shiyi
 *
 */

public class WebDriverHelper {

	private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	
	public static void setWebDriver(WebDriver webDriver) {
		driver.set(webDriver);
	}
	
	public static WebDriver getWebDriver() {
		return driver.get();
	}
}
