package selenium.base.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import selenium.base.GlobleVargs;
import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;

public class WebDriverFactoryTest {
	
	@Test
	public void test() {
		System.setProperty(GlobleVargs.chromeDriverPropertyKey, GlobleVargs.chromeDriverPath_v79);
		Optional<WebDriver> driver = WebDriverFactory.getWebDriver(BrowserType.Chrome);
		Assert.assertNotNull(driver.get());
	}
}
