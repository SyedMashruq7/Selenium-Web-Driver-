package DataDriven;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadingDataFromExcel {

	public static void main(String[] args) throws IOException {

		FileInputStream file1 = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		int totalCells = sheet.getRow(1).getLastCellNum();

		System.out.println("number of rows: " + totalRows);
		System.out.println("number of cells: " + totalCells);

		for (int r = 0; r <= totalRows; r++) {
			XSSFRow currentRow = sheet.getRow(r);
			for (int c = 0; c < totalCells; c++) {
				XSSFCell cell = currentRow.getCell(c);
				System.out.println(cell.toString());
			}
		}

		workbook.close();
		file1.close();
//
//		FileInputStream file2 = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\Book2.xlsx");
//
//		XSSFWorkbook workbook2 = new XSSFWorkbook();
//		XSSFSheet sheet2 = workbook2.createSheet("Dynamic Data");
//		Scanner sc = new Scanner(System.in);
//		System.out.println("Enter No.of Rows ");
//		int rowsCount = sc.nextInt();
//		System.out.println();
//		int columnCount = sc.nextInt();
//		for (int i = 0; i <= rowsCount; i++) {
//			XSSFRow currentRow = sheet2.createRow(i);
//
//			for (int j = 0; i < columnCount; j++) {
//				XSSFCell cell = currentRow.createCell(j);
//				cell.setCellValue(sc.next());
//			}
//		}

	}

	public int getRows() throws IOException {

		FileInputStream file1 = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int totalRows = sheet.getLastRowNum();
		
		workbook.close();
		file1.close();

		return totalRows;

	}

	public int getCols() throws IOException {

		FileInputStream file1 = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		int totalCols = sheet.getRow(1).getLastCellNum();
		
		workbook.close();
		file1.close();

		return totalCols;

	}
	
	public String getCellData(int rownum, int colnum) throws IOException {
		
		FileInputStream file1 = new FileInputStream(System.getProperty("user.dir") + "\\testdata\\TestData.xlsx");
		XSSFWorkbook workbook = new XSSFWorkbook(file1);
		XSSFSheet sheet = workbook.getSheet("Sheet1");
		XSSFRow row =sheet.getRow(rownum);
		XSSFCell cell = row.getCell(colnum);
		
		DataFormatter  formatter = new DataFormatter();
		String data;
			
		try {
			data=formatter.formatCellValue(cell);
		}
		catch(Exception e){
			data="";	
		}
		
		workbook.close();
		file1.close();
		
		return data;
		
	}

}
