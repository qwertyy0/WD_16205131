package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection1 {
	Connection conn;
	Statement stmt;
	PreparedStatement pre;

	public DBConnection1() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// com.mysql.cj.jdbc.Driver
			// "org.h2.Driver"
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Connection getConnection() {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/practice_training0?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8", "root", "123");
			// jdbc:mysql://localhost:3306/test?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8",
			// "root", "9517530*ysdss"
			// jdbc:h2:file:D:/temp/db/16205133","sa",""
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public Statement getStatement() {
		try {
			conn = this.getConnection();
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}

	public PreparedStatement getPreparedStatement(String sql) throws SQLException {
		conn = this.getConnection();
		pre = conn.prepareStatement(sql);
		return pre;
	}

	public int update(String sql) throws SQLException {
		stmt = this.getStatement();
		System.out.println("connection:" + sql);
		int n = stmt.executeUpdate(sql);
		conn.close();
		return n;
	}

	public ResultSet query(String sql) throws SQLException {
		System.out.println("connection:" + sql);
		stmt = this.getStatement();
		ResultSet rs = stmt.executeQuery(sql);
		return rs;
	}

	// 执行查询操作
	public ResultSet executeQuery(String preparedSql, String[] param) throws SQLException {
		// 处理sql，执行sql
		
		ResultSet rs = null;
		try {
			// 得到PreparedStatement对象
			pre = this.getPreparedStatement(preparedSql);
			/*pre = conn.prepareStatement(preparedSql);*/
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					// 为预编译sql设置参数
					pre.setString(i + 1, param[i]);
				}
			}
			// 执行sql语句
			System.out.println("connection:" + pre);
			rs = pre.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}

	// 执行更新操作
	public int excuteUpdate(String preparedSql, String[] param) {
		int num = 0;
//		conn = this.getConnection();
		try {
			pre = this.getPreparedStatement(preparedSql);
			if (param != null) {
				for (int i = 0; i < param.length; i++) {
					pre.setString(i + 1, param[i]);
				}
			}
			System.out.println("connection:" + pre);
			num = pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return num;
	}

	public void closeConnection() throws SQLException {
		if (conn != null)
			conn.close();
	}
}
