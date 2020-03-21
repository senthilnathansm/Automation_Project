package Automation_Testcase_Redbus;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class OrangeHRM {
	public WebDriver driver;
	String baseUrl = "https://opensource-demo.orangehrmlive.com/index.php/auth/login";
	ChromeOptions options;

	String[][] data = { { "Admin", "admin123" }, { "min", "admin123" }, { "Admin", "admi" }, { "Senthil", "Senthil" } };

	@DataProvider(name = "loginData")
	public String[][] loginprovider() {
		return data;

	}

	@BeforeTest
	public void loginHRM() {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		System.setProperty("webdriver.chrome.driver", "F:\\Redbus_Project\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
	}

	@Test(dataProvider = "loginData")
	public void checkusernamepass(String uName, String uPass) {
		driver.findElement(By.id("txtUsername")).sendKeys(uName);

		driver.findElement(By.id("txtPassword")).sendKeys(uPass);

		driver.findElement(By.id("btnLogin")).click();
		String ActualTitle = driver.getCurrentUrl();
		String ExpectedTitle = "https://opensource-demo.orangehrmlive.com/index.php/dashboard";
		Assert.assertEquals(ActualTitle,ExpectedTitle);
		System.out.println("Assert passed");
		WebDriverWait wait = new WebDriverWait(driver, 20);
		WebElement clickwelcom;
		clickwelcom = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("welcome")));
		clickwelcom.click();
		WebElement logoutbtn = driver.findElement(By.xpath("//*[@id='welcome-menu']/ul/li[2]/a"));
		Actions action = new Actions(driver);
		Action mousemovetoLOGOUT = action.moveToElement(logoutbtn).build();
		mousemovetoLOGOUT.perform();
		logoutbtn.click();

	}

}
