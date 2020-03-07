package adminDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import adminDAO.DBConnection;
import entity.Teacher;

public class teacherInfo {

	// 教师登陆检查
	public static boolean loginCheck(String username, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from teacher where teacher_id=? and teacher_psd=?";
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

	// 获取当前登陆教师账号的ID
	public static Teacher getTeacher(String username, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from teacher where teacher_id=? and teacher_psd=?";
		Teacher teacher = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				teacher = new Teacher();
				teacher.setTeacher_id(rs.getString(1));
				teacher.setTeacher_psd(rs.getString(2));
				teacher.setTeacher_major(rs.getString(3));
				teacher.setTeacher_name(rs.getString(4));
				teacher.setTeacher_phone(rs.getString(5));
				teacher.setTeacher_qq(rs.getString(6));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return teacher;
	}

	public static List<Teacher> getAllTeacher() {
		List<Teacher> teacherList = new ArrayList<>();
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from teacher";
		Teacher teacher = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				teacher = new Teacher();
				teacher.setTeacher_id(rs.getString(1));
				teacher.setTeacher_major(rs.getString(3));
				teacher.setTeacher_name(rs.getString(4));
				teacher.setTeacher_phone(rs.getString(5));
				teacher.setTeacher_qq(rs.getString(6));
				teacherList.add(teacher);
			}
			rs.close();
			ps.close();
			connection.close();
			return teacherList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/**
	 * 将excel文件数据导入到数据库
	 */
	public static boolean insertDataIntoDbByExcel(List<Teacher> teacherList) {

		Connection connection = DBConnection.getDBConnection();
		boolean insertSucceed = false;
		String sql = "INSERT INTO teacher (teacher_id,teacher_name,teacher_major,teacher_phone,teacher_qq) VALUES (?,?,?,?,?)";
		int rows = 0;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			for (int i = 0; i < teacherList.size(); i++) {
				ps.setString(1, teacherList.get(i).getTeacher_id());
				ps.setString(2, teacherList.get(i).getTeacher_name());
				ps.setString(3, teacherList.get(i).getTeacher_major());
				ps.setString(4, teacherList.get(i).getTeacher_phone());
				ps.setString(5, teacherList.get(i).getTeacher_qq());
				rows = ps.executeUpdate();
				if (rows != 1) {// 插入数据出现错误
					return false;
				}
			}
			insertSucceed = true;
		} catch (Exception e) {
			// TODO: handle exception
		}

		return insertSucceed;
	}

}
