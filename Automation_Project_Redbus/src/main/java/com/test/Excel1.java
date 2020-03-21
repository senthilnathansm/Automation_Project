package com.test;

import java.io.FileInputStream;
import java.io.IOException;

import org.testng.annotations.DataProvider;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

public class Excel1 {


	@DataProvider(name = "TestData")
	public Object[][] ExcelData() throws BiffException, IOException {

		String filepath = "F:\\Redbus_Project\\Project Folder\\"
				+ "Automation_Project_Redbus\\Excel_Files\\ExcelData.xls";

		FileInputStream Excelfile = new FileInputStream(filepath);
		Workbook Exbook = Workbook.getWorkbook(Excelfile);
		Sheet Exsheet = Exbook.getSheet("TestData");

		int Rows = Exsheet.getRows();
		int Columns = Exsheet.getColumns();

		String Testdata[][] = new String[Rows - 1][Columns];
		int count = 0;

		for (int i = 1; i < Rows; i++) {
			for (int j = 0; j < Columns; j++) {
				Cell Excell = Exsheet.getCell(j, i);
				Testdata[count][j] = Excell.getContents();
			}
			count++;
		}
		Excelfile.close();
		return Testdata;

	}

}
