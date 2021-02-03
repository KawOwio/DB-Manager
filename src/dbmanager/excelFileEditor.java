package dbmanager;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import javax.swing.JFrame;

import org.apache.poi.ss.formula.Formula;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class excelFileEditor {


	// save  Workbook
		public void saveWorkbook(Workbook workbook, FileOutputStream output) throws IOException {
		workbook.write(output);
		output.close();
		}
		
	// change cell Numeric value 
		public void editCellNumeric(Cell cell, Double value) {
		cell.setCellValue(value);
		}

	// change cell String value 
		public void editCellString(Cell cell, String value) {
		cell.setCellValue(value);
		}

	// change cell Numeric value 
		public void editCellBoolean(Cell cell, Boolean condition) {
		cell.setCellValue(condition);
		}

	// change cell Date value 
		public void editCellDate(Cell cell, Date date) {
		cell.setCellValue(date);
		}

	// change cell Numeric value 
		public void editCellFormula(Cell cell,  String formula) {
		cell.setCellFormula(formula);
		}

	// creates new Sheet 
		public void addSheet(Workbook workbook, String sheetName) {
		workbook.createSheet(sheetName);
		}
	
	// adds row at the end
		public void addRow(Sheet sheet) {
			sheet.createRow(sheet.getLastRowNum() + 1);
		}
		
	//  adds new Column at the end
		public void addColumn(Sheet sheet, String columnName) {
			for(Row row : sheet) {
				row.createCell(row.getLastCellNum());
				if (row.getRowNum() == 0 ) {
					row.getCell(row.getLastCellNum() - 1).setCellValue(columnName);
				}
			}
		}	
	
	// TODO adds row somewhere in the sheet
		public void addRow(Workbook workbook, int rowNr) {
			
		}
	
		
	// TODO adds new Column somewhere in the sheet
		public void addColumn(Workbook workbook, int columnNr) {
							
		}		
		
	// TODO Copy some sheet at the end
		public void copySheet(Workbook workbook, Sheet sheetToCopy) {
									
		}	
		
	// TODO Copies some sheet somewhere in the workbook
		public void copySheet(Workbook workbook, Sheet sheetToCopy, int whereToCopy) {
											
		}
		
		// TODO Copies some row at the end of this sheet
		public void copyRow(Workbook workbook, int rowToCopy) {
											
		}
		// TODO Copies some row somewhere in the sheet
		public void copyRow(Workbook workbook, int rowToCopy, int whereToCopy) {
											
		}
		// TODO Copies some row somewhere in the workbook
		public void copyRow(Workbook workbook, Sheet whereToCopy, int rowToCopy, int whereToCopy) {
											
		}
		// TODO Copies some column at the end of this sheet
		public void copyColumn(Workbook workbook, int columnToCopy) {
											
		}
		// TODO Copies some column somewhere in the sheet
		public void copyColumn(Workbook workbook, int columnToCopy, int whereToCopy) {
											
		}
		// TODO Copies some column somewhere in the workbook
		public void copyColumn(Workbook workbook, Sheet whereToCopy, int colomnToCopy, int whereToCopy) {
											
		}
		
		
}           
