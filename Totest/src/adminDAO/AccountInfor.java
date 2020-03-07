package adminDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import adminClass.Admin;

public class AccountInfor {

	public static Admin showAdmin(String adminId) {
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from admin where admin_id=?";
		Admin admin = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, adminId);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdminId(rs.getString(1));
				admin.setAdminName(rs.getString(4));
				admin.setAdminPhone(rs.getString(5));
				admin.setAdminqq(rs.getString(3));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return admin;
	}

	// 管理员登陆验证
	public static boolean loginCheck(String username, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from admin where admin_id=? and admin_pwd=?";
		boolean checkSuccess = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				checkSuccess = true;
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return checkSuccess;
	}

	// 获取当前登陆的管理员的ID（工号）
	public static Admin getAdmin(String username, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from admin where admin_id=? and admin_pwd=?";
		Admin admin = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				admin = new Admin();
				admin.setAdminId(rs.getString(1));
				admin.setAdminName(rs.getString(4));
				admin.setAdminPhone(rs.getString(5));
				admin.setAdminqq(rs.getString(3));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return admin;
	}

	public static boolean changeAdminInfo(String id, String username, String adminlogo, String telephone, String qq) {
		Connection connection = DBConnection.getDBConnection();
		String sql = "update admin set admin_name=? , admin_phone=? , admin_qq=? WHERE admin_id=?";
		boolean succeed = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, telephone);
			ps.setString(3, qq);
			ps.setString(4, id);
			int rows = ps.executeUpdate();
			if (rows > 0) {
				succeed = true;
			}
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return succeed;
	}

	public static boolean changeAdminPswd(String id, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "update admin set admin_pwd=? where admin_id=?";
		boolean checkSuccess = false;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, pswd);
			ps.setString(2, id);
			int rows = ps.executeUpdate();
			if (rows>0) {
				checkSuccess = true;
			}
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return checkSuccess;
	}

}
