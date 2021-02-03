package dbmanager;

import java.io.FileOutputStream;
import java.util.Calendar;

import org.odftoolkit.simple.SpreadsheetDocument;
import org.odftoolkit.simple.table.Cell;
import org.odftoolkit.simple.table.Table;


public class odfFileEditor {

		// save  Workbook
			public void saveWorkbook(SpreadsheetDocument workbook, FileOutputStream output) throws Exception {
			workbook.save(output);
			}
			
		// change cell Numeric value 
			public void editCellNumeric(Cell cell, Double value) {
			cell.setDoubleValue(value);
			}

		// change cell String value 
			public void editCellString(Cell cell, String value) {
			cell.setStringValue(value);
			}

		// change cell Numeric value 
			public void editCellBoolean(Cell cell, Boolean condition) {
			cell.setBooleanValue(condition);
			}

		// change cell Date value 
			public void editCellDate(Cell cell, Calendar date) {
			cell.setDateValue(date);
			}

		// change cell Numeric value 
			public void editCellFormula(Cell cell,  String formula) {
			cell.setFormula(formula);
			}

		// creates new Sheet 
			public void addSheet(SpreadsheetDocument workbook, String sheetName) {
			Table table  = Table.newTable(workbook);
			table.setTableName(sheetName);
			}
		
		// adds row at the end
			public void addRow(Table sheet) {
				sheet.appendRow();
			}
			
		//  adds new Column at the end
			public void addColumn(Table sheet, String columnName) {
				sheet.appendColumn();
			}	

}
