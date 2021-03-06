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
		System.setProperty(WebDriverPath.chromeDriverPropertyKey, WebDriverPath.chromeDriverPath_v83);
		WebDriver driver = WebDriverFactory.buildWebDriver(BrowserType.Chrome);
		Assert.assertNotNull(driver);
	}
}
