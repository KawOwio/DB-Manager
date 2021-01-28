package dbmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import org.apache.commons.io.FileUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

import javax.swing.JButton;
import java.awt.BorderLayout;

public class WriteToWord {

	private static String fileName;
	private static String filePath;

	public static void main(String[] args) throws InvalidFormatException, IOException {

		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);

		openWordFile(frame);
		replaceValuesFromExcel();
		// replaceValuesFromMySQL();
	}

	public static void openWordFile(JFrame frame) {
		JFileChooser fileChooser = new JFileChooser();
		int selected = fileChooser.showOpenDialog(frame);

		if (selected == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			String[] splittedData = path.split("\\.");

			if (splittedData.length > 0) {
				if (splittedData[1].equalsIgnoreCase("docx")) {
					// replaceValue(path);
					filePath = path;
					fileName = fileChooser.getSelectedFile().getName();
				}
			}
		}
	}

	public static void replaceValuesFromMySQL() {
		System.out.println(filePath);
		// Get data from MySQL
		MySQL db = new MySQL();
		ArrayList<String> columnTypes = db.getColumnTypes();
		ArrayList<String> columnNames = db.getColumnNames();

		// Set data to 2D ArrayList of Strings
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columnNames.size(); i++) {
			values.add(db.getValues(columnNames.get(i)));
		}

		int columns = values.size();
		int rows = values.get(0).size();

		replaceValues(rows, columns, columnNames, values);
	}

	public static void replaceValuesFromExcel() {
		// TODO: change to user input path and name
		String tmpPath = "/home/student/Excel-1.xlsx";

		// Getting all sheets from Excel
		List<Object> sheets = new ArrayList<Object>();
		try {
			sheets = (List<Object>) excelToJavaImport.excelToJava(tmpPath);
		} catch (IOException e) {
			e.printStackTrace();
		}

		// Getting data out of sheets
		ArrayList<ArrayList<Object>> excelData = (ArrayList<ArrayList<Object>>) sheets.get(0);

		// Get rows and columns
		int rows = excelData.size();
		int columns = excelData.get(0).size();

		// Get columnNames
		ArrayList<String> columnNames = new ArrayList<String>();
		for (int i = 0; i < columns; i++) {
			columnNames.add(excelData.get(0).get(i).toString());
		}

		// Remove a row with columns in it
		excelData.remove(0);
		rows--;

		// Get all values as strings
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>(excelData.size());

		// Convert list of objects to list of strings
		for (int i = 0; i < rows; i++) {
			ArrayList<String> strings = new ArrayList<>(excelData.get(i).size());
			for (Object object : excelData.get(i)) {
				strings.add(Objects.toString(object, null));
			}
			values.add(strings);
		}

		// 'Rotate' list for consistent passing
		ArrayList<ArrayList<String>> rotatedValues = new ArrayList<ArrayList<String>>(values.size());
		for (int y = 0; y < values.get(0).size(); y++) {
			ArrayList<String> rotate = new ArrayList<String>();
			for (int x = 0; x < values.size(); x++) {
				rotate.add(values.get(x).get(y));
			}
			rotatedValues.add(rotate);
		}

		replaceValues(rows, columns, columnNames, rotatedValues);
	}

	public static void replaceValues(int rows, int columns, ArrayList<String> columnNames,
			ArrayList<ArrayList<String>> values) {
		// Go through every entry in the database
		for (int i = 0; i < rows; i++) {
			try {
				System.out.println("in");
				// Make path for copied files in a separate folder
				String copyPath = filePath.replace(fileName,
						fileName.replace(".docx", "-copies/" + fileName.replace(".docx", "-" + (i + 1) + ".docx")));

				// Copy template file
				File source = new File(filePath);
				File destination = new File(copyPath);
				FileUtils.copyFile(source, destination);

				// Make changes in the new file
				FileInputStream fis = new FileInputStream(copyPath);
				XWPFDocument output = new XWPFDocument(fis);

				System.out.println("copied");
				// Go through every paragraph
				List<XWPFParagraph> par = output.getParagraphs();
				for (XWPFParagraph p : par) {

					// Go through every run and replace text
					List<XWPFRun> runs = p.getRuns();
					if (runs != null) {
						for (XWPFRun r : runs) {
							String text = r.getText(0);
							System.out.println(text);
							if (text != null) {
								for (int x = 0; x < columns; x++) {
									System.out.println("C: " + columnNames.get(x) + " X: " + values.get(x).get(i));
									text = text.replace("{" + columnNames.get(x) + "}", values.get(x).get(i));
								}
								text = text.replace("{DateToday}", getDate());
								r.setText(text, 0);
							}
						}
					}

					// Go through tables (if any) and replace text in them
					for (XWPFTable tbl : output.getTables()) {
						for (XWPFTableRow row : tbl.getRows()) {
							for (XWPFTableCell cell : row.getTableCells()) {
								for (XWPFParagraph tblPar : cell.getParagraphs()) {
									for (XWPFRun r : tblPar.getRuns()) {
										String text = r.getText(0);
										if (text != null) {
											for (int x = 0; x < columns; x++) {
												text = text.replace("{" + columnNames.get(x) + "}",
														"" + values.get(x).get(i));
											}
											text = text.replace("{DateToday}", getDate());
											r.setText(text, 0);
										}
									}
								}
							}
						}
					}
				}

				output.write(new FileOutputStream(copyPath));
				output.close();
				System.out.println("done");

			} catch (FileNotFoundException ex) {
				System.out.print(ex.getMessage());
			} catch (IOException ex1) {
				System.out.print(ex1.getMessage());
			}
		}
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		return dateFormat.format(today).toString();
	}
}
