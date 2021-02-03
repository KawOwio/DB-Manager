package dbmanager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.swing.JFrame;

public class JavaToMySQL {

	public Connection conn;
	/*
	 * ASK USER TO PROVIDE ALL THE INFO!
	 */
	public String username = "dbm"; // dynamically provided by user
	public String password = "dbmapp"; // dynamically provided by user
	public String databaseName = "fromODS"; // dynamically provided by user

	public JavaToMySQL() { // creating new database
		if (!"".equals(username)) {
			try {
				conn = DriverManager.getConnection(
						"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8",
						username, password);
				conn.setAutoCommit(false);
				Statement st = conn.createStatement();
				String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
				System.out.println(sql);
				st.executeUpdate(sql);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void fillTheDatabase(String tableName, List<ArrayList<Object>> sheet) {
		try {
			String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			ArrayList<Object> columnNames = sheet.get(0);
			for (int i = 0; i < columnNames.size(); i++) {
				if (columnNames.get(i).equals("") || columnNames.get(i).equals(" ")) {
					columnNames.remove(columnNames.get(i));
					i--;
				}
			}
			System.out.println(columnNames);
			for (int i = 0; i < columnNames.size(); i++) {
				columnNames.set(i, "`" + ((String) columnNames.get(i)).trim() + "`");
			}
			String argumentsToCreateColumns = "(";
			ArrayList<String> dataTypes = new ArrayList<>();
			for (int i = 0; i < columnNames.size(); i++) { // creating columns in database table

				String type = "";

				// getting data type from the data in second row (first row of actual data) -
				// TRICKY!!!
				if (((ArrayList<Object>) sheet.get(1)).get(i) instanceof Double) {
					type = " decimal(65,2), ";
				} else if (((ArrayList<Object>) sheet.get(1)).get(i) instanceof Integer) {
					type = " int(255), ";
				} else {
					if (((String) ((ArrayList<Object>) sheet.get(1)).get(i)).isEmpty())
						((List<Object>) sheet.get(1)).set(i, " ");
					type = " varchar(255), ";
				}
				dataTypes.add(type);
				argumentsToCreateColumns += columnNames.get(i) + type;
			}
			argumentsToCreateColumns = argumentsToCreateColumns.substring(0, argumentsToCreateColumns.length() - 2)
					+ ")";
			String sql = "CREATE TABLE IF NOT EXISTS " + tableName + argumentsToCreateColumns;
			System.out.println(dataTypes);
			System.out.println(sql);
			st.executeUpdate(sql);

			String argumentsColumnNames = "("; // column names for INSERT string
			for (Object columnName : columnNames) {
				argumentsColumnNames += columnName + ", ";
			}
			argumentsColumnNames = argumentsColumnNames.substring(0, argumentsColumnNames.length() - 2) + ")";
			System.out.println(argumentsColumnNames);

			sheet.remove(0);
			for (int a = 0; a < sheet.size(); a++) {
				String dataToFillColumns = " VALUES("; // data for INSERT string
				for (int i = 0; i < columnNames.size(); i++) {
					List<Object> rowContent = (List<Object>) sheet.get(a);
					dataToFillColumns += "'" + rowContent.get(i).toString() + "', ";
				}
				dataToFillColumns = dataToFillColumns.substring(0, dataToFillColumns.length() - 2) + ")";
				System.out.println(dataToFillColumns);
				sql = "insert into " + tableName + argumentsColumnNames + dataToFillColumns;
				System.out.println(sql);
				PreparedStatement insertStmt = conn.prepareStatement(sql);
				insertStmt.executeUpdate();
				conn.commit();
				System.out.println(insertStmt);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws Exception {
		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);

		JavaToMySQL test = new JavaToMySQL();
		boolean isItXLSX = false;
		String excelFilePath;
		String odfFilePath;
		LinkedHashMap<String, List<ArrayList<Object>>> database;

		if (isItXLSX) {
			excelFilePath = excelToJavaImport.openFile(frame, "xlsx");
			database = excelToJavaImport.excelToJava(excelFilePath);
		} else {
			odfFilePath = excelToJavaImport.openFile(frame, "ods");
			database = odfToJava.odfToJavaImport(odfFilePath);
		}

		Iterator<Map.Entry<String, List<ArrayList<Object>>>> itr = database.entrySet().iterator();
		while (itr.hasNext()) {
			Entry<String, List<ArrayList<Object>>> pair = itr.next();
			String key = pair.getKey();
			List<ArrayList<Object>> sheet =  database.get(key);
			test.fillTheDatabase(key, sheet);
		}

		// List<Object> sheet = (List<Object>) wholeDatabase.get(0); // whole sheet as
		// ArrayList
		// List<Object> row = (List<Object>) sheet.get(3); // whole row as ArrayList
		// Object value = row.get(15); // exact value from row
		// System.out.println(value);
		// System.out.println(value.getClass());

	}

}
