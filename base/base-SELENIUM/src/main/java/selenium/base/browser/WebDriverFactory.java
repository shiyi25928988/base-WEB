package selenium.base.browser;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

import selenium.base.GlobleVargs;
import selenium.base.WebDriverPath;

/**
 * @author shiyi
 *
 */
public class WebDriverFactory {

	private static WebDriver webDriver;
	
	public static WebDriver buildWebDriver(BrowserType type) {
		
		switch (type) {
		case Chrome:
			System.setProperty(WebDriverPath.chromeDriverPropertyKey, WebDriverPath.chromeDriverPath_v79);
			webDriver = new ChromeDriver();
			break;
		case Edge:
			webDriver = new EdgeDriver();
			break;
		case Firefox:
			webDriver = new FirefoxDriver();
			break;
		case IE:
			webDriver = new InternetExplorerDriver();
			break;
		case Opera:
			webDriver = new OperaDriver();
			break;
		case Safari:
			webDriver = new SafariDriver();
			break;
		default:
			webDriver = null;
			break;
		}
		return webDriver;
	}
	
	/**
	 * @return
	 */
	public static WebDriver getWebDriverInstance() {
		return webDriver;
	}
}
