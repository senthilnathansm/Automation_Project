package Redbus_Utility_Excel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.Test;

public class ExcelUtil_for_Write {
	
	public static void for_SearchBus(WebDriver driver, String screenshotName) throws IOException {
		try {
			// CLASSNAME.screen("screenshot");
			//Seacrch_Bus.screen(driver, "Senthil");
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
	
	@Test
	public void excelforwrite() {
		int i,j=0;
		// String testDatafile =
		// System.getProperty("F:\\Redbus_Project\\ExcelforWrite.xlsx");
		File file = new File("F:\\Redbus_Project\\ExcelforWrite.xlsx");
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sh = wb.createSheet();
		
		//sh.createRow(0).createCell(0).setCellValue("Age");
		
		try {
			FileOutputStream fos = new FileOutputStream(file);
			wb.write(fos);
			fos.close();
		} catch (Exception e) {

			e.printStackTrace();
		}
	}
}
