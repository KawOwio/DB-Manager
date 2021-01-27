package dbmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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

		openFile(frame);
		replaceValue();

		// JButton btnOpenFile = new JButton("Open file");
		// frame.getContentPane().add(btnOpenFile, BorderLayout.SOUTH);

		// btnOpenFile.addActionListener(new ActionListener() {

		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// openFile(frame);
		// }

		// });
	}

	public static void openFile(JFrame frame) {
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

	public static void replaceValue() {
		// TODO: find a file name w/o extension
		System.out.println(filePath);
		// Get data from MySQL
		MySQL db = new MySQL();
		ArrayList<String> columnTypes = db.getColumnTypes();
		ArrayList<String> columnNames = db.getColumnNames();
		filePath.lastIndexOf("/");
		// Set data to 2D ArrayList of Strings
		ArrayList<ArrayList<String>> values = new ArrayList<ArrayList<String>>();
		for (int i = 0; i < columnNames.size(); i++) {
			values.add(db.getValues(columnNames.get(i)));
		}

		int columns = values.size();
		int rows = values.get(0).size();

		// Go through every entry in the database
		for (int i = 0; i < rows; i++) {
			try {
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
									text = text.replace("{" + columnNames.get(x) + "}", values.get(x).get(i));
								}

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
}
