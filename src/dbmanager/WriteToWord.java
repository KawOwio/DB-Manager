package dbmanager;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
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

	public static void main(String[] args) throws InvalidFormatException, IOException {

		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);

		String name = "COMPANY NAME";
		String path = "";

		path = openFile(frame);
		replaceValue(path);

		// JButton btnOpenFile = new JButton("Open file");
		// frame.getContentPane().add(btnOpenFile, BorderLayout.SOUTH);

		// btnOpenFile.addActionListener(new ActionListener() {

		// @Override
		// public void actionPerformed(ActionEvent arg0) {
		// openFile(frame);
		// }

		// });
	}

	public static String openFile(JFrame frame) {
		JFileChooser fileChooser = new JFileChooser();
		int selected = fileChooser.showOpenDialog(frame);

		if (selected == JFileChooser.APPROVE_OPTION) {
			String path = fileChooser.getSelectedFile().getAbsolutePath();
			String[] splittedData = path.split("\\.");

			if (splittedData.length > 0) {
				if (splittedData[1].equalsIgnoreCase("docx")) {
					// replaceValue(path);
					return path;
				}
			}
		}
		return "";
	}

	public static void replaceValue(String path) {
		// TODO: make a loop so it goes through every item in the database
		// TODO: replace values with data from mysql/excel
		// text.replace("{" + mysql.getColumn(i).getName() + "}",
		// mysql.getColumn(i).getRow(y).getValue());
		try {
			String copyPath = path.replace(".docx", "-new.docx");

			// Copy template file
			File source = new File(path);
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
							text = text.replace("{name}", "COMPANY NAME");
							text = text.replace("{number}", "69696969");
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
										text = text.replace("{name}", "COMPANY NAME");
										text = text.replace("{number}", "69696969");
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

	// XWPFDocument doc = new XWPFDocument();
	// XWPFParagraph tmpPar = doc.createParagraph();
	// XWPFRun tmpRun = tmpPar.createRun();
	// tmpRun.setText("Hello");
	//
	// String folder = "/home/student/";
	// String fileName = "123.docx";
	//
	// File f = new File(folder);
	// if (!f.exists()) {
	// System.out.println("Created folder: " + folder);
	// f.mkdirs();
	// }
	//
	// FileOutputStream out;
	// try {
	// out = new FileOutputStream(new File(folder + "111"));
	// doc.write(out);
	// doc.close();
	// out.close();
	//
	// System.out.println("Wrote to: " + folder + fileName);
	// } catch (FileNotFoundException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// } catch (IOException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
}
