package extra;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class GetdatafromExcelFile {
	public static void main(String[] args) throws EncryptedDocumentException, IOException {
      
		FileInputStream fis = new FileInputStream("Path of excel File");
		
		Workbook wb = WorkbookFactory.create(fis);

		Sheet sh = wb.getSheet("org");
		
		Row row = sh.getRow(4);
		
		Cell cell = row.getCell(0);
		
		String data = cell.getStringCellValue();
	
		System.out.println(data + (int)(Math.random() * 999));

	}

}
