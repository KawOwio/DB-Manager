package dbmanager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MySQL {

	protected Connection conn;
	private ArrayList<String> columnTypes = new ArrayList<>();
	private ArrayList<String> columnNames = new ArrayList<>();
	/*
	 * ASK USER TO PROVIDE ALL THE INFO!
	 */
//	String username = "dbm"; // dynamically provided by user
//	String password = "dbmapp"; // dynamically provided by user
//	String databaseName = "dbmanager"; // dynamically provided by user
//	String databaseName = "test"; // dynamically provided by user
	String username;
	String password;
	String databaseName;
	String tableName;

	public MySQL(String username, String password, String databaseName, String tableName) {

		this.username = username;
		this.password = password;
		this.databaseName = databaseName;
		this.tableName = tableName;

		// Scanner sc = new Scanner(System.in);
		try {

//			conn = DriverManager.getConnection(
//					"jdbc:mysql://localhost/?autoReconnect=true&serverTimezone=UTC&characterEncoding=utf8", username,
//					password);

//			String url = "jdbc:mysql://localhost/" + databaseName;
//			conn = DriverManager.getConnection(url, username, password);

			String url = "jdbc:mysql://localhost/" + databaseName;
			conn = DriverManager.getConnection(url, username, password);

			conn.setAutoCommit(false);

//			databaseName = "dbmanager"; // dynamically provided by user
//			tableName = "Info"; // dynamically provided by user
//			tableName = "Test"; // dynamically provided by user

			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT * from " + databaseName + "." + tableName);
			ResultSetMetaData rsmd = (ResultSetMetaData) rs.getMetaData();
			int numOfColumns = rsmd.getColumnCount();
			// System.out.println("Num of columns: " + numOfColumns); // for testing
			// purposes
			columnTypes = new ArrayList<>();
			columnNames = new ArrayList<>();
			for (int i = 1; i <= numOfColumns; i++) {
				String columnName = rsmd.getColumnName(i); // for testing purposes
				String dataTypeOfColumn = rsmd.getColumnTypeName(i);
				columnTypes.add(dataTypeOfColumn);
				columnNames.add(columnName);
				// System.out.println(columnName + " has data type " + dataTypeOfColumn); // for
				// testing purposes

			}
			// System.out.println(columnTypes.toString());
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

	public ArrayList<String> getValues(String columnName) {
		ArrayList<String> rtnList = new ArrayList<>();
		String sql = "SELECT " + columnName + " FROM " + databaseName + "." + tableName + "";
		// System.out.println(sql);
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

	public ArrayList<String> getTableNames() {
		ArrayList<String> rtnList = new ArrayList<>();
		String sql = "SELECT table_name FROM information_schema.tables WHERE table_schema = '" + databaseName + "';";
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

	public void addColumn(String columnName, String dataType) throws SQLException {
		String url = "jdbc:mysql://localhost/" + databaseName;
		conn = DriverManager.getConnection(url, username, password);
		conn.setAutoCommit(false);
		Statement st = conn.createStatement();
		String sql = "ALTER TABLE " + tableName + " ADD " + columnName + " " + dataType + ";";
		System.out.println(sql);
		st.executeUpdate(sql);
		System.out.println("executeUpdate");
		conn.commit ();
		System.out.println("commit");
	}

	public ArrayList<ArrayList<String>> getRowContent(String columnName, String data) throws SQLException {
        String url = "jdbc:mysql://localhost/" + databaseName;
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);

        ArrayList<String> rowContent = new ArrayList<>();
        int rowIndex = 0;
        ArrayList<String> columnValues = getValues(columnName);
        for (int i = 0; i < columnValues.size(); i++) {
            if (data.equals((columnValues).get(i))) {
                rowIndex = i;
            }
        }
        for (String colName : columnNames) {
            rowContent.add(getValues(colName).get(rowIndex));
        }

        ArrayList<ArrayList<String>> allTheInfo = new ArrayList<>();
        allTheInfo.add(columnNames);
        allTheInfo.add(columnTypes);
        allTheInfo.add(rowContent);

        // for (int i = 0; i < columnNames.size(); i++) {
        //
        // System.out.println(allTheInfo.get(0).get(i));
        // System.out.println(allTheInfo.get(1).get(i));
        // System.out.println(allTheInfo.get(2).get(i));
        // System.out.println();
        // }
        
        return allTheInfo;
    }

	public void addRow(String columnName, Object data) throws SQLException {
        String url = "jdbc:mysql://localhost/" + databaseName;
        conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        Statement st = conn.createStatement();
        String sql = "INSERT INTO "+ databaseName + "." + tableName + " (" + columnName + ") VALUES ('" + data + "');"; 
        System.out.println(sql);
        st.executeUpdate(sql);
        conn.commit();
    }

 	public static void main(String[] args) throws SQLException {
 		MySQL t = new MySQL("dbm", "dbmapp", "Test", "Test");
 		// t.addColumn("newColumn1", "varchar(50)");
 		
 		// t.addColumn("test56", "int");
 		// t.addColumn("newblablabla", "decimal(50, 2)");
 		
 		// t.getRowContent("test1", "2");
 		
 		t.addRow("idTest", 6);
 	}


}