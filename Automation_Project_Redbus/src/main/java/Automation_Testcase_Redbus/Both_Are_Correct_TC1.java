package Automation_Testcase_Redbus;

	import java.io.File;
	import java.io.IOException;
	import java.text.SimpleDateFormat;
	import java.util.Date;
	import java.util.Iterator;
	import java.util.Set;
	import java.util.concurrent.TimeUnit;
	import org.openqa.selenium.By;
	import org.openqa.selenium.OutputType;
	import org.openqa.selenium.TakesScreenshot;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.chrome.ChromeOptions;
	import org.openqa.selenium.io.FileHandler;
	import org.testng.annotations.BeforeTest;
	import org.testng.annotations.Test;

import Libarary.Redbus_TestCase_Excel;
import Page_Class_ForTC.Login_Page_ObjectRepository;
import Page_Class_ForTC.Screenshot_Pgr;
import jxl.write.Label;
	import jxl.write.WriteException;
	import jxl.write.biff.RowsExceededException;

	public class Both_Are_Correct_TC1 extends Redbus_TestCase_Excel {
		static WebDriver driver;
		String baseUrl = "https://www.redbus.in/";
		ChromeOptions options;
		
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
			//************************************
			//Both_Are_Correct_TC1.screen("screenshot");
			Redbus_TestCase_Excel.screen("screenshot000");
			//************************************
			String Main_Page_Title = driver.getTitle();
			System.out.println("The Main Page Title is " + "** " + Main_Page_Title + "**");

			if (Main_Page_Title.equalsIgnoreCase("Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India")) {
				System.out.println("The Testcase PASS");
			} else {
				System.out.println("The Testcase FAIL");
			}
		}

		@Test(priority = 1, description = "Testcase One")
		public void Both_Are_Correct() throws InterruptedException, IOException {

			Login_Page_ObjectRepository loginpageOR = new Login_Page_ObjectRepository();
			
			Thread.sleep(3000);
			
			loginpageOR.iconProfile(driver).click();
			//Both_Are_Correct_TC1.screen("screenshot");
			//this.screen11("screenshotforBoth");
			Redbus_TestCase_Excel.screen("summa");
			loginpageOR.signInLink(driver).click();
	
			
			driver.switchTo().frame(2);
			
			loginpageOR.clickGoogleButton(driver).click();
			//Both_Are_Correct_TC1.screen("screenshot");
			
			String mainWindow = driver.getWindowHandle();
			Set<String> set = driver.getWindowHandles();
			// Using Iterator to iterate with in windows
			Iterator<String> itr = set.iterator();
			while (itr.hasNext()) {
				String childWindow = itr.next();
				// Compare whether the main windows is not equal to child window. If
				// not equal, we will close.
				if (!mainWindow.equalsIgnoreCase(childWindow)) {
					driver.switchTo().window(childWindow);

					String username = writablesh.getCell(2, 1).getContents();
					System.out.println("The First Username " + username);
					
					//Send the Username into text box
					loginpageOR.usernameTextBox(driver).sendKeys(username);
					//takepic.screen11("screenshotforBoth");
					
					//Get the Title of the page
					String Google1page = driver.getTitle();
					System.out.println("The Main Page Title is " + "** " + Google1page + "**");
					
					
					//Click the Next Btn in UserNAme page
					loginpageOR.nextBtninUserNamePage(driver).click();
					//takepic.screen11("screenshotforBoth");
					
					String Google2page = driver.getTitle();
					System.out.println("The Main Page Title is " + "** " + Google2page + "**");
					
					String password = writablesh.getCell(3, 1).getContents();
					System.out.println("The First Password " + password);
					
					
					//Send the password to the password text box
					loginpageOR.passwordtextbox(driver).sendKeys(password);
					//takepic.screen11("screenshotforBoth");
					
					//Click Next Button in Password Page
					loginpageOR.clicknextBtninPwdPage(driver).click();
					
				//	takepic.screen11("screenshotforBoth");
					// System.out.println(driver.switchTo().window(childWindow).getTitle());
				}
			}
			driver.switchTo().window(mainWindow);
			driver.switchTo().defaultContent();
			String Iframpage = driver.getTitle();
			
			System.out.println("The Iframpage is " + "** " + Iframpage + "**");
		//	takepic.screen11("screenshotforBoth");
			
			//Click to close the iFrame window in main page.
			loginpageOR.closetheIFrameWindow(driver).click();
			
		//	takepic.screen11("screenshotforBoth");
			if (Iframpage.equalsIgnoreCase("Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India")) {
				System.out.println("The Testcase PASS");
			} else {
				System.out.println("The Testcase FAIL");
			}
			Thread.sleep(1000);
			driver.quit();
		}
	}

