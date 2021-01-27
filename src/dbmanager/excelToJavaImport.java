package dbmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelToJavaImport {

	public static void main(String args[]) throws IOException  {
		
		 String excelFilePath = "/home/student/workspace/DB-Manager/test.xlsx";
		 
	    
	            FileInputStream inputStream = new FileInputStream(excelFilePath);
	 
	            Workbook workbook = new XSSFWorkbook(inputStream);
	            
	            Sheet sheet = workbook.getSheetAt(0);
	            
	            Map<Integer, List<String>> data = new HashMap<>();
	            int i = 0;
	            for (Row row : sheet) {
	                data.put(i, new ArrayList<String>());
	                for (Cell cell : row) {
	                    switch (cell.getCellType()) {
	                        case STRING: 
	                        	data.get(Integer.valueOf(i)).add(cell.getRichStringCellValue().getString());
	                        	// System.out.print(cell.getRichStringCellValue().getString() + "\t\t");
	                        	break;
	                        case NUMERIC: 
	                        	if (DateUtil.isCellDateFormatted(cell)) {
	                        	    data.get(i).add(cell.getDateCellValue() + "");
	                        	   // System.out.print("\t" +cell.getDateCellValue() + "\t\t");
	                        	    
	                        	} else {
	                        	    data.get(i).add(cell.getNumericCellValue() + "");
	                        	   // System.out.print("\t" + cell.getNumericCellValue() +  "\t\t");
	                        	}
	                        	break;
	                        case BOOLEAN: 
	                        	data.get(i).add(cell.getBooleanCellValue() + "");
	                        //	System.out.print(cell.getBooleanCellValue() +  "\t\t");
	                        	break;
	                        case FORMULA: 
	                        	data.get(i).add(cell.getCellFormula() + "");
	                        	//System.out.print(cell.getCellFormula() + "\t\t");
	                        	break;
	                        default: data.get(Integer.valueOf(i)).add(" ");
	    
	                    }
	            
	                }
	                System.out.println();
	                i++;
	            }
	            System.out.println(data.toString());
		// TODO Auto-generated constructor stub

}
}