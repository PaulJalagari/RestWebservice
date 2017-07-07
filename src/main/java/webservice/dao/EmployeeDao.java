package webservice.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class EmployeeDao {
	public static void main(String [] args){
		
	}
	public Connection getConnnection() {
		Connection connection = null;

		try {
			String connectionURL = "jdbc:mysql://localhost:3306/employeedb";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			connection = DriverManager.getConnection(connectionURL, "root", "ahextech");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.getLocalizedMessage();
		}
		return connection;
	}
}
