package Automation_Testcase_Redbus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import Page_Class_ForTC.ConfigFileReader;
import Page_Class_ForTC.Login_Page_ObjectRepository;
import Redbus_Utility_Excel.ExcelUtil_for_Write;

public class Search_Bus extends ExcelUtil_for_Write {
	static WebDriver driver;
	ChromeOptions options;
	String month = "Mar 2020";
	String month1 = "Apr 2020";
	String date = "20";
	public XSSFSheet sh;
	ConfigFileReader configFileReader;
	public XSSFWorkbook wb;
public Cell cell;
	@BeforeTest
	public void Lauchandopensite() throws Exception {
		options = new ChromeOptions();
		options.addArguments("--disable-notifications");
		configFileReader= new ConfigFileReader();
		System.setProperty("webdriver.chrome.driver",configFileReader.getDriverPath());
		driver = new ChromeDriver(options);
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get("https://www.redbus.in/");
		// Search_Bus.screen("screenshot");
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_MainPage");
	}

	@Test(priority = 1)
	public void searchbus() throws InterruptedException, IOException {
		// *[contains(@class,'bus-type')]

		// select to place**********************
		Login_Page_ObjectRepository loginpageOR = new Login_Page_ObjectRepository();

		// Send value to From Place Text Box
		loginpageOR.fromPlace(driver).sendKeys("CMBT");
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_FromPlace");
		// ****Build Selenium Jar to resolve incase of error-> Go to Fix Project
		// in hover>
		WebDriverWait wait = new WebDriverWait(driver, 20);
		List<WebElement> fromsuggest;
		fromsuggest = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("src")));

		for (WebElement suggest1 : fromsuggest) {
			if (suggest1.getText().equalsIgnoreCase("CMBT")) {
				suggest1.click();
			}
		}
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot");

		// select to place
		loginpageOR.toPlace(driver).sendKeys("Madiwala, Bang");
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_ToPlace");

		// Click the Destination Contains Text in Dropdown.
		loginpageOR.clicktheDestContainsTEXT(driver).click();
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_DestContainsText");

		// To Click the Onward Button in the Main Page
		loginpageOR.clickOnwardbutton(driver).click();
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_ClickOnwardsbtn");

		// For Onward Date
		while (true) {
			String text = driver.findElement(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[2]"))
					.getText();
			if (text.equals(month)) {
				break;
			} else {
				driver.findElement(By.xpath("//*[@id='rb-calendar_onward_cal']/table/tbody/tr[1]/td[3]")).click();
			}
		}

		// Click the Day in Date Picker in Onward picker
		loginpageOR.picktheDayinDatePickerforOnward(driver).click();
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_PickUP");

		// For Return Date
		// Click on Dest Date Picker Button
		loginpageOR.clickDestbutton(driver).click();

		while (true) {
			String text = driver.findElement(By.xpath("//*[@id='rb-calendar_return_cal']/table/tbody/tr[1]/td[2]"))
					.getText();
			if (text.equals(month1)) {
				break;
			} else {
				driver.findElement(By.xpath("//*[@id='rb-calendar_return_cal']/table/tbody/tr[1]/td[3]/button"))
						.click();
			}
		}
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_BeforeDayDate");
		// Pick the Day in Date Picker for Destination Date.
		loginpageOR.picktheDayinDatePickerforDest(driver).click();
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_AfterDayDate");

		// Click the Search Button in the Main Page
		loginpageOR.SearchButtonInMainPage(driver).click();
		ExcelUtil_for_Write.for_SearchBus(driver, "Screenshot_SearchBtn");

		String Title = driver.getTitle();
		System.out.println("The Title of the page is " + Title);

		if (Title.equalsIgnoreCase("Search Bus Tickets")) {
			System.out.println("Test Case Pass");
		} else {
			System.out.println("Test case Fail");
		}
	}

	@Test(priority = 2)
	public void get_The_Travels_Details() throws IOException {
		List<WebElement> travelsname = driver.findElements(By.xpath("//div[contains(@class,'travels lh-24')]"));
		System.out.println("The Total num of travelsnames " + travelsname.size());
		int countoftravelsname = travelsname.size();
		System.out.println(countoftravelsname);
		for (WebElement webElement : travelsname) {
			String name = webElement.getText();
			// System.out.print("The List of Travels Names are :");
			System.out.println(name);
		}
	}

	@Test(priority = 3)
	public void get_The_Start_Time(String[] args) throws IOException {
		List<WebElement> starttime = driver.findElements(By.xpath("//div[contains(@class,'dp-time f-19')]"));
		
		System.out.println("The Total num of travelsnames " + starttime.size());
		int countofstarttime = starttime .size();
		System.out.println(countofstarttime);
		for (WebElement webElement1 : starttime) {
			String starttime1 = webElement1.getText();
			// System.out.print("The List of Start times are :");
			System.out.println(starttime1);
		}
	}
	@Test(priority = 4)
	public void get_The_Boarding_Point() throws IOException {
		List<WebElement> boardingpoint = driver.findElements(By.xpath("//div[contains(@class,'dp-loc')]"));
		System.out.println("The Total num of travelsnames " + boardingpoint.size());
		int countofboardingpoint = boardingpoint.size();
		System.out.println(countofboardingpoint);
		for (WebElement webElement2 : boardingpoint) {
			String boardingpoint1 = webElement2.getText();
			// System.out.print("The List of Boarding Points are :");
			System.out.println(boardingpoint1);
		}
	}

	@Test(priority = 5)
	public void get_The_Departure_Time() throws IOException {
		List<WebElement> deptime = driver.findElements(By.xpath("//div[contains(@class,'bp-time f-19')]"));
		System.out.println("The Total num of travelsnames " + deptime.size());
		int countofdeptime = deptime.size();
		System.out.println(countofdeptime);
		for (WebElement webElement3 : deptime) {
			String deptime1 = webElement3.getText();
			// System.out.print("The List of Departure Times are :");
			System.out.println(deptime1);
		}
	}

	@Test(priority = 6)
	public void get_The_Destination_Place() throws IOException {
		List<WebElement> desplace = driver.findElements(By.xpath("//div[contains(@class,'bp-loc l')]"));
		System.out.println("The Total num of travelsnames " + desplace.size());
		int countofdesplace = desplace.size();
		System.out.println(countofdesplace);
		for (WebElement webElement4 : desplace) {
			String desplace1 = webElement4.getText();
			// System.out.print("The List of Destination Places are :");
			System.out.println(desplace1);
		}
	}

	@Test(priority = 7)
	public void get_The_Bus_Fare() throws IOException {
		List<WebElement> busfare = driver.findElements(By.xpath("//span[contains(@class,'f-19 f-bold')]"));
		System.out.println("The Total num of travelsnames " + busfare.size());
		int countofbusfare = busfare.size();
		System.out.println(countofbusfare);
		for (WebElement webElement5 : busfare) {
			String busfare1 = webElement5.getText();
			// System.out.print("The List of Bus Fare are :");
			System.out.println(busfare1);
		}
		driver.close();
	}
}
