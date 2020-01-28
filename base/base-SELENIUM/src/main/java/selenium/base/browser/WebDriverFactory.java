package selenium.base.browser;

import java.util.Optional;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.safari.SafariDriver;

public class WebDriverFactory {

	private static WebDriver webDriver;

	
	public static Optional<WebDriver> getWebDriver(BrowserType type) {
		
		switch (type) {
		case Chrome:
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
		Optional<WebDriver> opt = Optional.ofNullable(webDriver);
		return opt;
	}
}
