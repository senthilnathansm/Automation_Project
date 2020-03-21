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
import jxl.write.Label;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class Password_Only_CorrectTC3 extends Redbus_TestCase_Excel {
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
		Password_Only_CorrectTC3.screen("screenshot");
		String Main_Page_Title = driver.getTitle();
		System.out.println("The Main Page Title is " + "** " + Main_Page_Title + "**");

		if (Main_Page_Title.equalsIgnoreCase("Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India")) {
			System.out.println("The Passwordonlycorrect PASS");
		} else {
			System.out.println("The Passwordonlycorrect FAIL");
		}
	}

	@Test(priority = 1, description = "Testcase One")
	public void Passwordonlycorrect() throws InterruptedException, IOException {
		
		Login_Page_ObjectRepository loginpageOR = new Login_Page_ObjectRepository();
		
		Thread.sleep(3000);
		
		
		loginpageOR.iconProfile(driver).click();
		Password_Only_CorrectTC3.screen("screenshot");
		
		loginpageOR.signInLink(driver).click();
		Password_Only_CorrectTC3.screen("screenshot");
		Thread.sleep(1000);
		
		driver.switchTo().frame(2);
		
		loginpageOR.clickGoogleButton(driver).click();
		Password_Only_CorrectTC3.screen("screenshot");
		
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
				
				String username = writablesh.getCell(2, 3).getContents();
				System.out.println("The First Username " + username);
				
				//Send the Username into text box
				loginpageOR.usernameTextBox(driver).sendKeys(username);
				Password_Only_CorrectTC3.screen("screenshot");
				
				String Google1page = driver.getTitle();
				System.out.println("The Main Page Title is " + "** " + Google1page + "**");
				
				//Click the Next Btn in UserNAme page
				loginpageOR.nextBtninUserNamePage(driver).click();
				Password_Only_CorrectTC3.screen("screenshot");
				
				
				WebElement forGettingError = driver.findElement(By.xpath("//*[@id='view_container']/div/div/div[2]/div/"
						+ "div[1]/div/form/span/section/div/div/div[1]/div/div[2]/div[2]/div"));
				String StoreErrorMsg = forGettingError.getText();
				System.out.println("The Error Msg is :" + "**" + StoreErrorMsg + "**");
				Password_Only_CorrectTC3.screen("screenshot");
				
				if (StoreErrorMsg.equalsIgnoreCase("Couldn't find your Google Account")) {
					System.out.println("The Password_Only_Correct Fail due to Incorrect Username");
					driver.close();
					driver.switchTo().window(mainWindow);
					driver.switchTo().defaultContent();
					
					//Click to close the iFrame window in main page.
					loginpageOR.closetheIFrameWindow(driver).click();
				} else {
					System.out.println("The Password_Only_Correct pass, the system allow incorrect username");
				}
				String Google2page = driver.getTitle();
				System.out.println("The Main Page Title is " + "** " + Google2page + "**");
			}
		}
		Thread.sleep(1000);
		driver.quit();
	}

	public static void screen(String screenshotName) throws IOException {
		// CLASSNAME.screen("screenshot");
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
		Date date = new Date();
		String screenshotNameFormat = screenshotName + " " + formatter.format(date);
		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(screenshot, new File("C:\\Users\\Senthilnathan\\workspace\\Automation_Project_Redbus\\"
				+ "Screenshots_For_ALL\\Screenshot_TC3_Password_Only_Correct\\" + screenshotNameFormat + ".png"));
	}
}
