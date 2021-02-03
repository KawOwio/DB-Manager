package dbmanager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

import org.apache.poi.ss.usermodel.Sheet;

public class Random {
	
	public static void main (String [] args) throws IOException {

		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);
		String excelFilePath = excelToJavaImport.openFile(frame, "xlsx");
		columnValues("Adreses", 3, excelFilePath );
		
	}
	
	
	
	public static List<Object> columnValues (String sheetName, int colNum, String excelFilePath) throws IOException{
		LinkedHashMap<String, List<ArrayList<Object>>> table = excelToJavaImport.excelToJava(excelFilePath);
		List<Object> columnValues = new ArrayList<>();
		for(int i = 1; i < table.get(sheetName).size(); i++) {
			columnValues.add(table.get(sheetName).get(i).get(colNum - 1));
		}
		System.out.println(columnValues);
		return columnValues;
}
}
	