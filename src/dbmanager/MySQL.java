package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class MySQL {
	protected Connection conn;
	private ArrayList<String> columnTypes;
	private ArrayList<String> columnNames;
	
	public MySQL() {
		// Scanner sc = new Scanner(System.in);
		try {
			/*
			 * ASK USER TO PROVIDE ALL THE INFO!
			 */
			String username = "dbm"; // dynamically provided by user
			String password = "dbmapp"; // dynamically provided by user

			conn = DriverManager.getConnection(
					"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", username,
					password);
			conn.setAutoCommit(false);

			String databaseName = "dbmanager"; // dynamically provided by user
			String tableName = "Info"; // dynamically provided by user

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from " + databaseName + "." + tableName);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int numOfColumns = rsmd.getColumnCount();
			System.out.println("Num of columns: " + numOfColumns); // for testing purposes
			columnTypes = new ArrayList<>();
			columnNames = new ArrayList<>();
			for (int i = 1; i <= numOfColumns; i++) {
				String columnName = rsmd.getColumnName(i); // for testing purposes
				String dataTypeOfColumn = rsmd.getColumnTypeName(i);
				columnTypes.add(dataTypeOfColumn);
				columnNames.add(columnName);
				System.out.println(columnName + " has data type " + dataTypeOfColumn); // for testing purposes

			}
			System.out.println(columnTypes.toString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<String> getColumnTypes() {
		return columnTypes;
	}
	
	public ArrayList<String> getColumnNames() {
		return columnNames;
	}

	public Person findPerson(int id) {

		Person person = new Person(0, null, null);

		String sql = "SELECT * FROM dbmanager.Info WHERE phoneNr = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			ResultSet results = preparedStatement.executeQuery();
			results.next();
			person = new Person(results.getInt(1), results.getString(2), results.getString(3));
			conn.commit();
		} catch (SQLException e) {
			return person;
		}
		return person;
	}

	public ArrayList<String> getValues(String columnName){
		ArrayList<String> rtnList = new ArrayList<>();
		String sql = "SELECT " + columnName + " FROM dbmanager.Info"; 
		System.out.println(sql);
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql);
			ResultSet results = preparedStatement.executeQuery();
			
			while (results.next()) {
				rtnList.add(results.getString(1));
			}
			conn.commit();
			
		} catch (SQLException e) {
			return rtnList;
		}
		return rtnList;
	}
	
	// TODO: change this later
	public boolean deletePerson(int id) {
		// #7 Write an sql statement that deletes teacher from database.
		boolean status = false;
		String sql = "DELETE from dbmanager.Info WHERE phoneNr = ?";
		PreparedStatement preparedStatement;
		try {
			preparedStatement = conn.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			int deleted = preparedStatement.executeUpdate();
			conn.commit();
			if (deleted > 0) {
				status = true;
			}
		} catch (SQLException e) {
			status = false;
		}
		return status;
	}

	public static void main(String[] args) {
		MySQL ne = new MySQL();
		// ne.deletePerson(33333333);
		ArrayList<String> num = new ArrayList<>();
		num = ne.getValues("number");
		System.out.println(num);
	}
}
