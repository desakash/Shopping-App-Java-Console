package java_project1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class dbConnection {
	public static Connection getConnection() {
		Connection con=null;
		try {
			//load drivers
			Class.forName("oracle.jdbc.OracleDriver");
			//connection to data Base
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","system");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
}
