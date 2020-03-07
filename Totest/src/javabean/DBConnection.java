package javabean;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBConnection {
	Connection conn;
	Statement stmt;
	PreparedStatement pre;
	public DBConnection(){
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		try {
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/practice_training0?useSSL=false&useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8","root","123");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public Statement getStatement(){
		try {
			conn=this.getConnection();
			stmt=conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return stmt;
	}
	public PreparedStatement getPreparedStatement(String sql) throws SQLException{
		conn=this.getConnection();
		pre=conn.prepareStatement(sql);
		return pre;
	}
	public int update(String sql) throws SQLException{
		stmt=this.getStatement();
		System.out.println("connection:"+sql);
		int u=stmt.executeUpdate(sql);
		conn.close();
		return u;
	}
	public ResultSet query(String sql) throws SQLException{
		System.out.println("connection:"+sql);
		stmt=this.getStatement();
		ResultSet rs=stmt.executeQuery(sql);
		return rs;
	}
	public void closeConnection() throws SQLException{
		if(conn!=null)
			conn.close();
	}
}
