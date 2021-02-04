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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;
import org.odftoolkit.odfdom.doc.OdfTextDocument;
import org.odftoolkit.odfdom.incubator.search.TextNavigation;
import org.odftoolkit.odfdom.incubator.search.TextSelection;

public class WriteToWord {

	public static void main(String[] args) {
		File excel = new File("/home/student/ODS-1.ods");
		File doc = new File("/home/student/ODF-1.odt");
		try {
			replaceValuesFromSpreadsheet(excel, doc);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void replaceValuesFromMySQL(MySQL db, File doc) {
		// Get data from MySQL
		ArrayList<String> columnNames = db.getColumnNames();

		// Set data to 2D ArrayList of Strings
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columnNames.size(); i++) {
			values.add(db.getValues(columnNames.get(i)));
		}

		int columns = values.size();
		int rows = values.get(0).size();

		replaceValues(doc, rows, columns, columnNames, values);
	}

	public static void replaceValuesFromSpreadsheet(File spreadsheet, File doc) {

		// Getting all sheets from Excel
		LinkedHashMap<String, List<ArrayList<Object>>> sheets = new LinkedHashMap<>();
		try {
			if (spreadsheet.getName().contains("xlsx")) {
				sheets = excelToJavaImport.excelToJava(spreadsheet.getAbsolutePath());
			} else if (spreadsheet.getName().contains("ods")) {
				sheets = odfToJava.odfToJavaImport(spreadsheet.getAbsolutePath());
			} else {
				System.out.println("WRONG FORMAT");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Getting data out of sheets
		ArrayList<ArrayList<Object>> excelData = (ArrayList<ArrayList<Object>>) sheets.values().toArray()[0];
		System.out.print(excelData);

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

		replaceValues(doc, rows, columns, columnNames, rotatedValues);
	}

	private static void replaceValues(File doc, int rows, int columns, ArrayList<String> columnNames,
			ArrayList<ArrayList<String>> values) {
		// Go through every entry in the database
		for (int i = 0; i < rows; i++) {
			try {
				String filePath = doc.getAbsolutePath();
				String fileName = doc.getName();
				String copyPath;

				String extension = "";

				// Make path for copied files in a separate folder
				// Check what file extension it is
				if (fileName.contains(".docx")) {
					copyPath = filePath.replace(fileName,
							fileName.replace(".docx", "-copies/" + fileName.replace(".docx", "-" + (i + 1) + ".docx")));
					extension = "docx";
				} else if (fileName.contains(".odt")) {
					copyPath = filePath.replace(fileName,
							fileName.replace(".odt", "-copies/" + fileName.replace(".odt", "-" + (i + 1) + ".odt")));
					extension = "odt";
				} else {
					System.out.println("wrong file format");
					return;
				}

				// Copy template file
				File source = new File(filePath);
				File destination = new File(copyPath);
				FileUtils.copyFile(source, destination);

				// If it's OpenOfficeDocument
				if (extension == "odt") {
					TextNavigation search;
					OdfTextDocument document = (OdfTextDocument) OdfTextDocument.loadDocument(doc);

					// Go through every column
					for (int x = 0; x < columns; x++) {
						String s = "&" + columnNames.get(x) + "&";
						System.out.println("Search: " + s);
						search = new TextNavigation(s, document);
						while (search.hasNext()) {
							TextSelection item = (TextSelection) search.getCurrentItem();
							System.out.println("Get: " + values.get(x).get(i));
							item.replaceWith(values.get(x).get(i));
						}

						search = new TextNavigation("&DateToday&", document);
						while (search.hasNext()) {
							TextSelection item = (TextSelection) search.getCurrentItem();
							item.replaceWith(getDate());
						}
					}

					document.save(copyPath);

				} else if (extension == "docx") {
					// Make changes in the new file
					FileInputStream fis = new FileInputStream(copyPath);
					XWPFDocument output = new XWPFDocument(fis);

					// Go through every paragraph
					List<XWPFParagraph> par = output.getParagraphs();

					for (XWPFParagraph p : par) {
						// Go through every run and replace text
						List<XWPFRun> runs = p.getRuns();
						if (runs != null) {
							for (XWPFRun r : runs) {
								String text = r.getText(0);
								if (text != null) {
									for (int x = 0; x < columns; x++) {
										text = text.replace("&" + columnNames.get(x) + "&", values.get(x).get(i));
									}
									text = text.replace("&DateToday&", getDate());
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
													text = text.replace("&" + columnNames.get(x) + "&",
															"" + values.get(x).get(i));
												}
												text = text.replace("&DateToday&", getDate());
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
				}

			} catch (FileNotFoundException ex) {
				System.out.print(ex.getMessage());
			} catch (IOException ex1) {
				System.out.print(ex1.getMessage());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static String getDate() {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date today = new Date();
		return dateFormat.format(today).toString();
	}
}
