package Page_Class_ForTC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class SignUp_PageClass {
	public String Testcase;
	public WritableSheet writablesh;
	public WritableWorkbook workbookcopy;

	// ***************************************For READ EXCELFILE
	// **************************
	@BeforeTest
	public void queryParameterization() throws IOException, BiffException, RowsExceededException, WriteException {

		// FileInputStream testfile = new
		// FileInputStream("F:\\Redbus_Project\\Data_Sheet.xls");
		FileInputStream testfile = new FileInputStream(
				"C:\\Users\\Senthilnathan\\workspace\\Automation_Project_Redbus\\Excel_Files\\Data_Sheet.xls");
		Workbook wbook = Workbook.getWorkbook(testfile);
		Sheet sheets = wbook.getSheet("SignupForGoogleAccount");
		int No_Rows = sheets.getRows();
		String inputdata[][] = new String[sheets.getRows()][sheets.getColumns()];
		// System.out.println("The No of Rows are " + No_Rows);

		// *************************************THE
		// END*********************************

		// For Writing data in Excel:::::

		 FileOutputStream testoutput = new
		 FileOutputStream("F:\\Redbus_Project\\Dataheetpy.xls");
		// System.out.println("Creating File One");

		 workbookcopy = Workbook.createWorkbook(testoutput);

		// System.out.println("Creating File 2");

		 writablesh = workbookcopy.createSheet("TestData", 0);

		// System.out.println("Creating File 3");

		for (int i = 0; i < sheets.getRows(); i++) {
			for (int k = 0; k < sheets.getColumns(); k++) {
				inputdata[i][k] = sheets.getCell(k, i).getContents();
				Label l1 = new Label(k, i, inputdata[i][k]);
				Label l2 = new Label(4, 0, "Results");
				writablesh.addCell(l1);
				writablesh.addCell(l2);
			}
		}
	}

	@AfterTest
	public void writeexcels() {
		try {
			workbookcopy.close();
		} catch (WriteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
