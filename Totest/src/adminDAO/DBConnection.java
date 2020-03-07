package adminDAO;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	public static Connection getDBConnection() {
		String driverName = "com.mysql.cj.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/practice_training0?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8";
		String usename = "root";
		String password = "123";
		Connection connection = null;
		try {
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, usename, password);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return connection;
	}
}
