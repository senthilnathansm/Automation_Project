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
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Page_Class_ForTC.SignUp_PageClass;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class SignUp_New_Account_Facebook extends SignUp_PageClass {

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
		// ************************************
		// Both_Are_Correct_TC1.screen("screenshot");
		// ************************************
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

		Thread.sleep(3000);

		WebElement userIcon;
		userIcon = driver.findElement(By.id("i-icon-profile"));
		userIcon.click();

		WebElement SignInLink;
		SignInLink = driver.findElement(By.id("signInLink"));
		SignInLink.click();

		driver.switchTo().frame(2);

		WebDriverWait wait = new WebDriverWait(driver, 20);

		WebElement ClickFacebooSignUpBtn;
		ClickFacebooSignUpBtn = driver.findElement(By.id("newFbId"));
		ClickFacebooSignUpBtn = wait.until(ExpectedConditions.elementToBeClickable(ClickFacebooSignUpBtn));
		ClickFacebooSignUpBtn.click();

		String mainWindow = driver.getWindowHandle();

		Set<String> set = driver.getWindowHandles();
		// Using Iterator to iterate with in windows
		Iterator<String> itr = set.iterator();
		while (itr.hasNext()) {
			String childWindow = itr.next();
			if (!mainWindow.equalsIgnoreCase(childWindow)) {
				driver.switchTo().window(childWindow);
				String Create_Page_Facebook_Account = driver.getTitle();
				System.out.println("Create_Page_Facebook_Account :" + "**" + Create_Page_Facebook_Account + "**");
				WebElement clickCreateNewAccountBtn;
				clickCreateNewAccountBtn = driver.findElement(By.xpath("//*[@id='content']/div[2]/a"));
				clickCreateNewAccountBtn.click();
				String New_Facebook_Window = driver.getTitle();
				System.out.println("New_Facebook_Window :" + "**" + New_Facebook_Window + "**");
				driver.close();
			}
		}
		driver.switchTo().window(mainWindow);
		driver.switchTo().defaultContent();

		String mainWindow1 = driver.getWindowHandle();
		Set<String> set1 = driver.getWindowHandles();
		Iterator<String> itr1 = set1.iterator();
		while (itr1.hasNext()) {
			String childWindow1 = itr1.next();
			if (!mainWindow.equalsIgnoreCase(childWindow1)) {
				driver.switchTo().window(childWindow1);
				String Create_Page_Facebook_Account = driver.getTitle();
				System.out.println("Create_Page_Facebook_Account :" + "**" + Create_Page_Facebook_Account + "**");
				Actions action = new Actions(driver);
				// First Name Text Box
				String FirstName = writablesh.getCell(0, 1).getContents();
				System.out.println("The First Name " + FirstName);
				WebElement Firstnametxtbox;
				Firstnametxtbox = driver.findElement(By.id("u_0_n"));
				Firstnametxtbox.sendKeys(FirstName);
				// Last Name Text Box
				String LastName = writablesh.getCell(1, 1).getContents();
				System.out.println("The Last Name " + LastName);
				WebElement Lastnametxtbox;
				Lastnametxtbox = driver.findElement(By.id("u_0_p"));
				Lastnametxtbox.sendKeys(LastName);
				// Email_Address Text Box
				String Email_Address = writablesh.getCell(2, 1).getContents();
				System.out.println("The Email_Address " + Email_Address);
				WebElement Email_Addresstxtbox;
				Email_Addresstxtbox = driver.findElement(By.id("u_0_s"));
				Email_Addresstxtbox.sendKeys(Email_Address);

				// ReEnter Email
				WebElement ReEnterEmail;
				ReEnterEmail = wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("u_0_v"))));
				ReEnterEmail.sendKeys(Email_Address);

				// Password Text Box
				String New_Password = writablesh.getCell(2, 1).getContents();
				// System.out.println("The New_Password " + New_Password);
				WebElement New_Passwordtextbox;
				New_Passwordtextbox = driver.findElement(By.id("u_0_x"));
				New_Passwordtextbox.sendKeys(New_Password);

				Select dateofbirth = new Select(driver.findElement(By.id("day")));
				dateofbirth.selectByVisibleText("24");

				Select monthofbirth = new Select(driver.findElement(By.id("month")));
				monthofbirth.selectByVisibleText("Jun");

				Select yearofbirth = new Select(driver.findElement(By.id("year")));
				yearofbirth.selectByVisibleText("1992");

				WebElement radiobutton;
				radiobutton = driver.findElement(By.id("u_0_7"));
				radiobutton.click();

				WebElement ClickSignUpButton;
				ClickSignUpButton = driver.findElement(By.id("u_0_14"));
				ClickSignUpButton.click();

				WebElement CheckPointSubmitButton;
				CheckPointSubmitButton = wait.until(
						ExpectedConditions.elementToBeClickable(driver.findElement(By.id("checkpointSubmitButton"))));
				CheckPointSubmitButton.click();

				WebElement FirstCaptcha;
				FirstCaptcha = driver.findElement(By.xpath("//*[@id='recaptcha-anchor']/div[1]"));
				Actions moveCaptcha = new Actions(driver);
				moveCaptcha.moveToElement(FirstCaptcha).click().build().perform();
				CheckPointSubmitButton.click();
			}
		}
		String Iframpage = driver.getTitle();
		System.out.println("The Iframpage is " + "** " + Iframpage + "**");
		// Both_Are_Correct_TC1.screen("screenshot");
		// driver.findElement(By.xpath("/html/body/div[7]/div/div[2]/div/div/div[2]/i")).click();
		// Both_Are_Correct_TC1.screen("screenshot");
		if (Iframpage.equalsIgnoreCase("Book Bus Travels, AC Volvo Bus, rPool & Bus Hire - redBus India")) {
			System.out.println("The Testcase PASS");
		} else {
			System.out.println("The Testcase FAIL");
		}
		Thread.sleep(1000);
		driver.quit();
	}
	/*
	 * public static void screen(String screenshotName) throws IOException{
	 * //CLASSNAME.screen("screenshot"); SimpleDateFormat formatter = new
	 * SimpleDateFormat("ddMMyyyyHHmmss"); Date date = new Date(); String
	 * screenshotNameFormat = screenshotName+" "+ formatter.format(date); File
	 * screenshot=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	 * FileHandler.copy(screenshot, new
	 * File("C:\\Users\\Senthilnathan\\workspace\\Automation_Project_Redbus\\" +
	 * "Screenshots_For_ALL\\Screenshot_Signup_Google_Account\\" +screenshotNameFormat+"
	 * .png")); }
	 */
}
