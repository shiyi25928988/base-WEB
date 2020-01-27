package selenium.base.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import selenium.base.browser.BrowserType;
import selenium.base.browser.WebDriverFactory;

public class WebDriverFactoryTest {
	
	@Test
	public void test() {
		
		Optional<WebDriver> driver = WebDriverFactory.getWebDriver(BrowserType.Chrome);
		Assert.assertNotNull(driver.get());
	}
}
