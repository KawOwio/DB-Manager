package dbmanager;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelToJavaImport {

	public static void main(String args[]) throws IOException   {

		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);
		
		String excelFilePath = openFile(frame);
		excelToJava(excelFilePath);
		
	}
		
	public static List<Object> excelToJava(String excelFilePath) throws IOException {
	            FileInputStream inputStream = new FileInputStream(excelFilePath);
	        
	 
	            Workbook workbook = new XSSFWorkbook(inputStream);
	            List<Object> sheets = new ArrayList <>();
	          
	           
	            for (int j = 0; j < workbook.getNumberOfSheets(); j++) {
	           Sheet sheet = workbook.getSheetAt(j);
	           List<ArrayList<Object>> excelData  = new ArrayList <>();
	            //sheets.add(new ArrayList<Object>());
	            //excelData.clear();
	            for(Row row :sheet) {
	            	excelData.add(new ArrayList<Object>());  
	            	for(Cell cell: row){ 
	            		excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
	                    switch (cell.getCellType()) {
	                    
	                    	case BLANK:
	                    	System.out.print("Adding BLANK"  + "[" + cell.getRowIndex() 
                        	+ "]" + cell.getColumnIndex() + "]");
                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
                        	break;
	                        case STRING: 
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getRichStringCellValue().getString());
	                        	System.out.print("Adding string" + cell.getRichStringCellValue().getString() + "[" + cell.getRowIndex() 
	                        	+ "]" + cell.getColumnIndex() + "]");
	                        	break;
	                        case NUMERIC: 
	                        	if (DateUtil.isCellDateFormatted(cell)) {
	                        		 excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getDateCellValue() + "");
	                        		 System.out.print("Adding date" + cell.getDateCellValue() + "[" + cell.getRowIndex() 
	 	                        	+ "]" + cell.getColumnIndex() + "]");
	                        	} else {
	                        		System.out.print("Adding numeric" + cell.getNumericCellValue() + "[" + cell.getRowIndex() 
		                        	+ "]" + cell.getColumnIndex() + "]");
	                        	    excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getNumericCellValue() + "");
	                        	}
	                        	break;
	                        case BOOLEAN: 
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getBooleanCellValue() + "");
	                        	System.out.print("Adding boolean" +  cell.getBooleanCellValue() + "[" + cell.getRowIndex() 
	                        	+ "]" + cell.getColumnIndex() + "]");
	                        	break;
	                        case FORMULA: 
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getCellFormula() + "");
	                        	System.out.print("Adding formula" +  cell.getCellFormula() + "[" + cell.getRowIndex() 
	                        	+ "]" + cell.getColumnIndex() + "]");
	                        	break;
	                        default: 
	                        	System.out.print("Adding empty"  + "[" + cell.getRowIndex() 
	                        	+ "]" + cell.getColumnIndex() + "]");
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
	                        	break;
	                    }
	                    System.out.println();
	            }
	            //	 sheets.add(excelData);
	            }
	            System.out.println(excelData);
	            System.out.println(excelData.size());
	            sheets.add(excelData);
	            }
	            workbook.close();
	            System.out.print(sheets.toString());
	          
				return sheets;
	            
}
	  
    public static String openFile(JFrame frame) {
		JFileChooser fileChooser = new JFileChooser();
		int selected = fileChooser.showOpenDialog(frame);

		if (selected == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			String[] splittedData = path.split("\\.");

			if (splittedData.length > 0) {
				if (splittedData[1].equalsIgnoreCase("xlsx")) {
					frame.dispose(); // close window
					frame.setVisible(false); // hide window
					// replaceValue(path);
					return path;
				}
			}
		}
		
		return "";
	}

}