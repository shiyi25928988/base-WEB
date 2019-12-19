package selenium.base.test;

import java.io.File;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeTest {
	
	private static final String driverPath = System.getProperty("user.dir") 
			+ File.separator + "src" 
			+ File.separator + "main" 
			+ File.separator + "resources" 
			+ File.separator + "drivers"
			+ File.separator + "chrome"
			+ File.separator + "chromedriver.exe";
	static {
		System.setProperty("webdriver.chrome.driver", driverPath);
	}
	private static WebDriver driver = new ChromeDriver();
	
	public static void main(String...strings) {
		//Convenient
		driver.get("https://selenium.dev");

		//Longer way
		driver.navigate().to("https://selenium.dev");
		
		driver.getCurrentUrl();
		
		driver.navigate().back();
		
		driver.navigate().forward();
		
		driver.navigate().refresh();
		
		driver.getTitle();
	}
}
