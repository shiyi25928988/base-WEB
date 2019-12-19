package selenium.base.test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {

	private static final String driverPath = System.getProperty("user.dir") + File.separator + "src" + File.separator
			+ "main" + File.separator + "resources" + File.separator + "drivers" + File.separator + "chrome"
			+ File.separator + "chromedriver.exe";
	static {
		System.setProperty("webdriver.chrome.driver", driverPath);
	}
	private static WebDriver driver = new ChromeDriver();

	public static void main(String... strings) {
//		// Convenient
//		driver.get("https://selenium.dev");
//
//		// Longer way
//		driver.navigate().to("https://selenium.dev");
//
//		driver.getCurrentUrl();
//
//		driver.navigate().back();
//
//		driver.navigate().forward();
//
//		driver.navigate().refresh();
//
//		driver.getTitle();

//		driver.get("http://www.google.com");
//		WebElement searchForm = driver.findElement(By.tagName("form"));
//		WebElement searchBox = searchForm.findElement(By.name("q"));
//		searchBox.sendKeys("webdriver");
//		
//		WebElement button  = searchForm.findElement(By.name("btnK"));
//		button.click();
		
        //WebDriver driver = new FirefoxDriver();
        try {
            // Navigate to Url
            driver.get("https://google.com");

            // Enter text "q" and perform keyboard action "Enter"
            driver.findElement(By.name("q")).sendKeys("q" + Keys.ENTER);
        } finally {
            driver.quit();
        }
	}
}
