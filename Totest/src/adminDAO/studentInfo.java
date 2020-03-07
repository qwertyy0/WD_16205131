package adminDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import adminDAO.DBConnection;
import javabean.Student;

public class studentInfo {
	// 学生登陆验证
	public static boolean loginCheck(String username, String pswd) {
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from student where sno=? and spsw=?";
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

	// 获取当前登陆的学生的信息
	public static Student getStudent(String username, String pswd) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		String sql = "select * from student where sno=? and spsw=?";
		Student student = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, pswd);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				student = new Student();
				student.setSno(rs.getString(1));
			}
			rs.close();
			ps.close();
			connection.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return student;
	}

	public static List<Student> getUnsubmitStudent() {
		List<Student> studentList = new ArrayList<>();
		Connection connection = DBConnection.getDBConnection();
		String sql = "SELECT sno,sname FROM student WHERE sno NOT IN (SELECT sno FROM stu_report)";
		Student student = null;
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			// 如果存在没有提交汇报的学生
			while (rs.next()) {
				student = new Student();
				student.setSno(rs.getString(1));
				student.setSna(rs.getString(2));
				studentList.add(student);
			}
			rs.close();
			ps.close();
			connection.close();
			return studentList;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
	}

	/* 将查询到的未提交的学生学号插入到表count_noreport_list中(未提交汇报学生信息统计表) */
	public static boolean insertUnsubmitStudent(String adminId, List<Student> studentList) {
		// TODO Auto-generated method stub
		Connection connection = DBConnection.getDBConnection();
		try {
			PreparedStatement ps = null;
			// 存在未提交报告的学生
			// 将信息插入数据库
			if (studentList.size() != 0) {
				String deleteData = "DELETE FROM count_noreport_list";
				ps = connection.prepareStatement(deleteData);
				int rows = ps.executeUpdate();
				if (rows >= 0) {// 清空表成功
					String insertData = "INSERT INTO count_noreport_list (admin_id,sno,count_time) VALUES (?,?,NOW())";
					int insertNum = 0;// 插入数据计数
					ps = connection.prepareStatement(insertData);
					for (int i = 0; i < studentList.size(); i++) {
						ps.setString(1, adminId);
						ps.setString(2, studentList.get(i).getSno());
						rows = ps.executeUpdate();
						if (rows > 0) {
							insertNum++;
						}
					}
					if (insertNum != studentList.size()) {// 判断全部数据是否已经全部插入数据库
						return false;
					}
				} else {// 清空表失败
					return false;
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return true;
	}
}
