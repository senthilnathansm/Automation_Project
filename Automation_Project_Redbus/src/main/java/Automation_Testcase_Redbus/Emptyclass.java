package Automation_Testcase_Redbus;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.Test;

import Page_Class_ForTC.Screenshot_Pgr;

public class Emptyclass extends Screenshot_Pgr{
	static WebDriver driver;
	ChromeOptions options;
	
	@Test
	public void Lauchandopensite() throws IOException {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "F:\\Redbus_Project\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.redbus.in/");
		Screenshot_Pgr.screen(driver, "Senthil");
		driver.quit();
	}


}
