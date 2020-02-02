package selenium.base.test;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import selenium.base.WebDriverPath;
import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;

public class WebDriverFactoryTest {
	
	@Test
	public void test() {
		System.setProperty(WebDriverPath.chromeDriverPropertyKey, WebDriverPath.chromeDriverPath_v79);
		WebDriver driver = WebDriverFactory.getWebDriver(BrowserType.Chrome);
		Assert.assertNotNull(driver);
	}
}
