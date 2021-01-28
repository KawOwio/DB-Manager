package dbmanager;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.swing.JFrame;

public class JavaToMySQL {

	protected Connection conn;
	/*
	 * ASK USER TO PROVIDE ALL THE INFO!
	 */
	String username = "dbm"; // dynamically provided by user
	String password = "dbmapp"; // dynamically provided by user
	String databaseName = "fromExcel";
	String tableName = "testTable";

	public JavaToMySQL() { // creating new database
		try {
			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", username,
					password);
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			String sql = "CREATE DATABASE IF NOT EXISTS " + databaseName;
			System.out.println(sql);
			st.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void fillTheDatabase(Map<Integer, List<String>> mapWithData) {
		try {
			String url = "jdbc:mysql://localhost:3306/" + databaseName + "?useSSL=false";
			conn = DriverManager.getConnection(url, username, password);
			conn.setAutoCommit(false);
			Statement st = conn.createStatement();
			List<String> columnNames = mapWithData.get(0);
			String argumentsToCreateColumns = "(";
			for (String columnName : columnNames) { 					// creating columns in database table
				if (columnName.contains(" ")) {
					String updatedColumnName = columnName.replace(" ", "_");
					columnName = updatedColumnName;
				}
				argumentsToCreateColumns += columnName + " varchar(255), ";
			}
			argumentsToCreateColumns = argumentsToCreateColumns.substring(0, argumentsToCreateColumns.length() - 2)
					+ ")";
			String sql = "CREATE TABLE IF NOT EXISTS " + tableName + argumentsToCreateColumns;
			System.out.println(sql);
			st.executeUpdate(sql);

			String argumentsColumnNames = "("; 							// column names for INSERT string
			for (String columnName : columnNames) {
				if (columnName.contains(" ")) {
					String updatedColumnName = columnName.replace(" ", "_");
					columnName = updatedColumnName;
				}
				argumentsColumnNames += columnName + ", ";
			}
			argumentsColumnNames = argumentsColumnNames.substring(0, argumentsColumnNames.length() - 2) + ")";

			String dataToFillColumns = " VALUES("; 						// data for INSERT string
			for (int i = 1; i < mapWithData.entrySet().size(); i++) {
				for (String data : mapWithData.get(i))
					dataToFillColumns += "'" + data + "', ";
				dataToFillColumns = dataToFillColumns.substring(0, dataToFillColumns.length() - 2) + ")";
				sql = "insert into " + tableName + argumentsColumnNames + dataToFillColumns;
				dataToFillColumns = " values(";
				PreparedStatement insertStmt = conn.prepareStatement(sql);
				insertStmt.executeUpdate();
				conn.commit();
				System.out.println(insertStmt);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void main(String[] args) throws IOException {
		final JFrame frame = new JFrame("Document Reader");
		frame.setSize(400, 400);
		frame.setLocation(750, 350);
		frame.setVisible(true);

		String excelFilePath = excelToJavaImport.openFile(frame);

		List<ArrayList<Object>> wholeDatabase = excelToJavaImport.excelToJava(excelFilePath);
		List<Object> sheet = (List<Object>) wholeDatabase.get(2).get(0);
		List<Object> row = (List<Object>) sheet.get(2);
		Object value = row.get(0);
		System.out.println(value);
		System.out.println(value.getClass());
		//test.fillTheDatabase(mapWithData);

	}

}
