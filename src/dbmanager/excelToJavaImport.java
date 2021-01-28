package dbmanager;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.poi.ss.formula.FormulaType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
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
	        sheets.add(new ArrayList<Object>());
	        for(Row row :sheet) {
	        	excelData.add(new ArrayList<Object>());  
	        	
	            for(Cell cell: row){ 
	            	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
	                switch (cell.getCellType()) {
	                	case BLANK:
	                		System.out.print("Adding BLANK"  + "[" + cell.getRowIndex()    
                        		+ "]" + cell.getColumnIndex() + "]");//for testing
                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
                        break;
	                	case STRING: 
	                		excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getRichStringCellValue().getString());
	                		System.out.print("Adding string" + cell.getRichStringCellValue().getString() + "[" + cell.getRowIndex() 
	                					+ "]" + cell.getColumnIndex() + "]");     //for testing
	                     break;
	                     case NUMERIC: 
	                        if (DateUtil.isCellDateFormatted(cell)) {
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), (Date)cell.getDateCellValue());
	                        	System.out.print("Adding date" + cell.getDateCellValue() + "[" + cell.getRowIndex() 
	 	                        		+ "]" + cell.getColumnIndex() + "]");      //for testing
	                        } else {
	                        	System.out.print("Adding numeric" + cell.getNumericCellValue() + "[" + cell.getRowIndex() 
		                        		+ "]" + cell.getColumnIndex() + "]");      //for testing
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), (int)cell.getNumericCellValue());
	                        }
	                        break;
	                        case BOOLEAN: 
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), (boolean)cell.getBooleanCellValue());
	                        	System.out.print("Adding boolean" +  cell.getBooleanCellValue() + "[" + cell.getRowIndex() 
	                        			+ "]" + cell.getColumnIndex() + "]");        //for testing
	                        break;
	                        case FORMULA:
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), cell.getCellFormula());
	                        	System.out.print("Adding formula" +  cell.getCellFormula() + "[" + cell.getRowIndex() 
	                        		+ "]" + cell.getColumnIndex() + "]");           //for testing
	                        break; 
	                        default: 
	                        	System.out.print("Adding empty"  + "[" + cell.getRowIndex() 
	                        		+ "]" + cell.getColumnIndex() + "]");             //for testing
	                        	excelData.get(cell.getRowIndex()).add(cell.getColumnIndex(), " ");
	                        break;
	                    }
	            
	                    System.out.println();            //for testing
	            }
	        }
	            System.out.println("ARRAYS: " + excelData);      //for testing
	            System.out.println("Izmers " + excelData.size());     //for testing
	            sheets.add(excelData);  
	            }
	            workbook.close();
	            System.out.println(sheets);  //for testing
	            System.out.println(sheets.get(0)); //for testing
	            System.out.println((sheets.get(2)));  // for testing
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