package com.test;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Page_Class_ForTC.Login_Page_ObjectRepository;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class login extends Excel{
	WebDriver driver;
	String baseUrl = "https://www.redbus.in/";
	ChromeOptions options;
	Map<String, Object[]> testresultsdata;

	@BeforeTest(description = "Testcase one open chrome and Broswer")
	public void openbrowser() throws RowsExceededException, WriteException, IOException {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "F:\\Redbus_Project\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test(dataProvider = "TestData")
	public void Both_Are_Correct(String Username, String password, String Test_Results)
			throws InterruptedException, IOException {
		Login_Page_ObjectRepository loginpageOR = new Login_Page_ObjectRepository();
		loginpageOR.iconProfile(driver).click();
		loginpageOR.signInLink(driver).click();
		driver.switchTo().frame(2);
		loginpageOR.clickGoogleButton(driver).click();
		String mainWindow = driver.getWindowHandle();
		Set<String> set = driver.getWindowHandles();
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			// Compare whether the main windows is not equal to child window. If
			// not equal, we will close.
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);

				loginpageOR.usernameTextBox(driver).sendKeys(Username);
				loginpageOR.nextBtninUserNamePage(driver).click();
				loginpageOR.passwordtextbox(driver).sendKeys(password);
				loginpageOR.clicknextBtninPwdPage(driver).click();
				String CurrentURL = driver.getCurrentUrl();
				System.out.println("The CurrentURL :"+CurrentURL+"***");
			}
		}
		driver.switchTo().window(mainWindow);
		driver.switchTo().defaultContent();
		loginpageOR.closetheIFrameWindow(driver).click();
		driver.quit();
	}
}
