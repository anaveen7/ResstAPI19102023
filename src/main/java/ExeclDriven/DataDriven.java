package ExeclDriven;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class DataDriven {
	
	//Identify test cases column by scanning the 1 row
	//Once column is identified then scan entire test case column to identify purchase testcase row

	public ArrayList<String> getData(String TestCaseName,String TestSheet) throws IOException {
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\TestDataResources\\testdata.xlsx");
		//FileInputStream fis= new FileInputStream("D:\\Practice\\PracticeRestAPI\\TestDataResources\\testdata.xlsx");
		ArrayList<String> a= new ArrayList<String>();
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		int sheets=workbook.getNumberOfSheets();
		for(int i=0;i<sheets;i++) {
			if(workbook.getSheetName(i).equalsIgnoreCase(TestSheet)) {
				
				XSSFSheet sheet=workbook.getSheetAt(i);
				//Identify test case column by scanning the entire 1st row
				Iterator<Row> rows=sheet.iterator(); // Sheet is a collection of rows
				Row firstrow=rows.next();
				Iterator<Cell> ce=firstrow.cellIterator(); // Row is collection of cells
				int k=0;
				int column =0;
				while(ce.hasNext()) {
					Cell value= ce.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCases")) {
						column=k;
						break;
					}
					k++;
				}
				//After you grab purchase test case row pull all the data of that row feed into test
				
				while(rows.hasNext()) {
					Row r=rows.next();
					if(r.getCell(column).getStringCellValue().equalsIgnoreCase(TestCaseName)) {
						Iterator<Cell> cv=r.cellIterator();
						while(cv.hasNext()) {
							Cell c=cv.next();
							if(c.getCellType()==CellType.STRING) {
						a.add(c.getStringCellValue());
							}
							else {
								a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
							}
						}
					}
				}
			}
		}
		return a;
	}

	private Object cellType() {
		// TODO Auto-generated method stub
		return null;
	}

}
