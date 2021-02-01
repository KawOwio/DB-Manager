package dbmanager;


import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;

import javax.swing.JFrame;

public class odfToJava {
	

	public static void main(String args[]) throws Exception   {

		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);
		
		String odfFilePath = excelToJavaImport.openFile(frame, "ods");
		odfToJavaImport(odfFilePath);
	}
	public static LinkedHashMap<String, Object> odfToJavaImport(String odfFilePath) throws Exception {
		SpreadsheetDocument tableDoc  = SpreadsheetDocument.loadDocument(odfFilePath);
	
		 LinkedHashMap<String, Object> sheets = new LinkedHashMap<String, Object>();
		
		 for (int i = 0 ; i < tableDoc.getSheetCount(); i++) {
			Table sheet = tableDoc.getSheetByIndex(i);
			List<ArrayList<Object>> tableData  = new ArrayList <>();
			System.out.println("Row COUNT " + sheet.getRowCount());
			System.out.println("COLUMN COUNT " + sheet.getColumnCount());
			
			int columnCount = columnCount(sheet);
			System.out.print("COOOLLL" + columnCount);

			for (int row = 0; row < sheet.getRowCount(); row++) {
					tableData.add(new ArrayList<Object>());  
					for(int column = 0; column < columnCount; column++) {
						Cell cell = sheet.getCellByPosition(column, row);
						System.out.println("COLUMN COUNT " + sheet.getColumnCount());
						System.out.println("VALUE TYPE AS STRING " + cell.getValueType());
						System.out.println("STRING TYPE " + cell.getStringValue());
						
						if (cell.getValueType() == null) {
							tableData.get(row).add(column, " ");
	                		System.out.println("Adding nothing"  + "[" + row 
	                					+ "][" + column + "]");     //for testing
	                		
						}
						else if  (cell.getValueType().equals("string")) {
							tableData.get(row).add(column, cell.getStringValue());
	                		System.out.println("Adding string" + cell.getStringValue() + "[" + row 
	                					+ "][" + column + "]");     //for testing
						}
						else if  (cell.getValueType().equals("float")) {
							if (cell.getDoubleValue() % 1 == 0) {
								tableData.get(row).add(column, cell.getDoubleValue().intValue());
		                		System.out.println("Adding long" + cell.getDoubleValue().longValue() + "[" + row 
		                					+ "][" + column + "]");     //for testing
							}
							else {
								tableData.get(row).add(column, cell.getDoubleValue());
								System.out.println("Adding double" + cell.getDoubleValue() + "[" + row 
	                					+ "][" + column + "]");     //for testing
							}
						}
						else if (cell.getValueType().equals("boolean")) {
							tableData.get(row).add(column, cell.getBooleanValue());
							System.out.println("Adding boolean" + cell.getBooleanValue() + "[" + row 
                					+ "][" + column + "]");     //for testing
						}
						else if (cell.getValueType().equals("date")) {
							
							tableData.get(row).add(column, cell.getDateValue().getTime().toString());
							System.out.println("Adding Date" + cell.getDateValue().getTime().toString() + "[" + row 
                					+ "][" + column + "]");     //for testing
							
						}
						else if (cell.getValueType().equals("currency")) {
							tableData.get(row).add(column, cell.getCurrencyValue());
							System.out.println("Adding currency" + cell.getCurrencyValue() + "[" + row 
                					+ "][" + column + "]");     //for testing
						} else {
							tableData.get(row).add(column, "");
	                		System.out.println("Adding nothing"  + "[" + row 
	                					+ "][" + column + "]");     //for testing
						}
							
					}
				}
				System.out.println("ARRAYS: " + tableData);      //for testing
	            System.out.println("Izmers " + tableData.get(1).size());     //for testing
	            sheets.put(sheet.getTableName(), tableData);  
			
			 }	
		
		System.out.println(sheets);
		
		return sheets;
		
	}
	
	public static int columnCount(Table sheet) {
		int column = sheet.getColumnCount();
		while((sheet.getCellByPosition(column, 0)).getValueType() == null){
			column--;
		}
		sheet.removeColumnsByIndex(column + 1, sheet.getColumnCount());
		
		return sheet.getColumnCount() ;
		
	}
}