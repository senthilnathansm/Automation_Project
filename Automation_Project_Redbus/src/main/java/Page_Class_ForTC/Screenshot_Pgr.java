package Page_Class_ForTC;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class Screenshot_Pgr {
	//static WebDriver driver;

	public static void screen(WebDriver driver, String screenshotName) throws IOException {
		try {
			// CLASSNAME.screen("screenshot");
			SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
			Date date = new Date();
			String screenshotNameFormat = screenshotName + " " + formatter.format(date);
			File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(screenshot, new File("F:\\Redbus_Project\\Project Folder\\Automation_Project_Redbus\\"
					+ "Screenshots_For_ALL\\Screenshot_SearchBus\\" + screenshotNameFormat + ".png"));
		} catch (NullPointerException e) {
			System.out.print("NullPointerException Caught");
		}
	}
}
