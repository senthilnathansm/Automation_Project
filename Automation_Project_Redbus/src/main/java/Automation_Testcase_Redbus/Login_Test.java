package Automation_Testcase_Redbus;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.test.Excel1;

import Page_Class_ForTC.Login_Page_ObjectRepository;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Login_Test extends Excel1 {
	static WebDriver driver;
	String baseUrl = "https://www.redbus.in/";
	ChromeOptions options;
	Map<String, Object[]> testresultsdata;

	@BeforeTest()
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
		Thread.sleep(1000);
		loginpageOR.iconProfile(driver).click();
		Thread.sleep(1000);
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

				String actual = driver.getCurrentUrl();
				String expected = "https://accounts.google.com/signin/v2/challenge/pwd?client_id=231171689615-idianhahjhk2s9rdlr1hrd9e2a09b3cj.apps.googleusercontent.com&as=0ysw8ITHRzi3qPki63Kztg&destination=https%3A%2F%2Fwww.redbus.in&approval_state=!ChRKTXdtUlVkakFQcnNnRWVjdThmZhIfZzI3T3F0LVFBVVlmOEhuU1JuY2dubW91VGZ1MEN4Yw%E2%88%99AF-3PDcAAAAAXmaEn4edQTV5-nZ3YJ6svwhKi_Hr739B&oauthgdpr=1&xsrfsig=ChkAeAh8T6GV0cE-AMoLHLdh7iMlNWU5MvhcEg5hcHByb3ZhbF9zdGF0ZRILZGVzdGluYXRpb24SBXNvYWN1Eg9vYXV0aHJpc2t5c2NvcGU&flowName=GeneralOAuthFlow&TL=AKCKxpEOPffUML8bpFSlOrdG3mvxTY63zGlPQc1FGJzoUa7-wIoh6i95tv1jMI1x&cid=1&navigationDirection=forward";

				try {
					Assert.assertEquals(actual, expected);
					System.out.println("The Test Case PASS");
				} catch (Exception e) {
					System.out.println("The Test Case FAIL");
				}
			}
		}
		driver.switchTo().window(mainWindow);
		driver.switchTo().defaultContent();
		loginpageOR.closetheIFrameWindow(driver).click();
		driver.quit();
	}
}
