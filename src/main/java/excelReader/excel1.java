/**
 * 
 */
package excelReader;

import org.testng.annotations.Test;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
/**
 * @author Niharika
 *
 * 
 */

public class excel1 {
	public static void main (String args[]) throws IOException{
	testdata("Sheet1","locator");
	System.out.println(testdata("Sheet1","locator"));
	}
	@Test
	public static String testdata(String testCaseName,String string) throws IOException {
	     
		  String content="";
		   File file=new File (System.getProperty("user.dir")+"\\src\\main\\java\\data\\TestData1.xlsx");
			 FileInputStream fs=new FileInputStream(file);
			 XSSFWorkbook wk = new XSSFWorkbook(fs);
			  XSSFSheet s1 = wk.getSheet(testCaseName);
			  
			 for(int i=0;i>=0;i++) {
				 XSSFRow r1 = s1.getRow(i);
				  XSSFCell c0 = r1.getCell(0);
			 if (string.equals(c0.getStringCellValue())) {
				 XSSFCell c1 = r1.getCell(1);
				  content= c1.getStringCellValue();
				 break;
			 }
			 if(c0.getStringCellValue().isEmpty()) {
				 break;
			 }
			 }
			return content;
		}	


}
	
