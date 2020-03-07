package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import adminDAO.DBConnection;
import entity.Post;
import entity.Student;
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

	// 获取当前登陆教师账号的信息
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

	// 获取当前登陆教师账号的信息(根据ID来查询)
		public static Teacher getTeacherById(String teacher_id) {
			// TODO Auto-generated method stub
			Connection connection = DBConnection.getDBConnection();
			String sql = "select * from teacher where teacher_id=?";
			Teacher teacher = null;
			try {
				PreparedStatement ps = connection.prepareStatement(sql);
				ps.setString(1, teacher_id);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					teacher = new Teacher();
					teacher.setTeacher_id(rs.getString(1));
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
	
	// 查询所有班级
	public static List<String> getallclass(String teacher_id) throws SQLException {
		ResultSet rsmid = null;
		DBConnection1 conn = new DBConnection1();
		// 查询出此老师所管理的班级编号。
		String sql = "SELECT class.class_id FROM teacher,class"
				+ " WHERE class.teacher_id = teacher.teacher_id AND teacher.teacher_id=?";
		try {
			rsmid = conn.executeQuery(sql, new String[] { teacher_id });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> allmyclass = new ArrayList<String>();
		rsmid.last();
		int allclassnum = 0;
		try {
			allclassnum = rsmid.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rsmid.absolute(0);
		rsmid.next();
		// 记录所有指导班级
		for (int i = 0; i < allclassnum; i++) {
			allmyclass.add(rsmid.getString(1));
			rsmid.next();
		}
		return allmyclass;
	}
	//按老师所点击的班级查询所有学生信息
	/**
	 * 
	 * @param class_id
	 * @return
	 * @throws SQLException
	 */
	public static List<Student> getmyclassstudent(String teacher_id) throws SQLException {
		List<Student> liststudent = new ArrayList<>();
		ResultSet rsmid = null;
		DBConnection1 conn = new DBConnection1();
		// 查询出此老师所管理的班级编号。
		String sql = "SELECT class.class_id FROM teacher,class"
				+ " WHERE class.teacher_id = teacher.teacher_id AND teacher.teacher_id=?";
		try {	
		rsmid = conn.executeQuery(sql, new String[] { teacher_id });
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List<String> allmyclass = new ArrayList<String>();
		
		rsmid.last();
		int allclassnum = 0;
		try {
			allclassnum = rsmid.getRow();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		rsmid.absolute(0);
		rsmid.next();
		// 记录所有指导班级
		for (int i = 0; i < allclassnum; i++) {
			allmyclass.add(rsmid.getString(1));
			rsmid.next();
			DBConnection1 conn1 = new DBConnection1();
			String sqllast = "select * from student where class_id=?";
			System.out.println(allmyclass.get(i));			
			ResultSet rslast = conn1.executeQuery(sqllast, new String[] { allmyclass.get(i) });
			while (rslast.next()) {
				Student student = new Student();
				student.setSno(rslast.getString(1));
				student.setClass_id(rslast.getString(2));
				student.setSname(rslast.getString(3));
				student.setSmajor(rslast.getString(4));
				student.setSphone(rslast.getString(5));
				student.setSqq(rslast.getString(6));
				student.setSpsw(rslast.getString(7));
				liststudent.add(student);
			}
		}
		return liststudent;
	}
	/**
	 * 修改老师个人信息
	 * @param teacher_id
	 * @param teacher_phone
	 * @param teacher_qq
	 * @return
	 */
	public boolean changeTeacherInfo(String teacher_id,String teacher_phone,String teacher_qq) {
		boolean flag = false;
		DBConnection1 conn = new DBConnection1();
		String sql = "update teacher set teacher_phone=?,teacher_qq=? where teacher_id=?";
		int i = conn.excuteUpdate(sql, new String[]{teacher_phone,teacher_qq,teacher_id});
		if(i!=0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 查询学生信息
	 * @param student_id
	 * @return
	 * @throws SQLException
	 */
	/*public Student getstudentmessage(String student_id) throws SQLException {
		Connection conn = DBConnection.getDBConnection();
		ResultSet rs = null;
		Student student= null;
		String sql = "select * from student where sno=?";
		PreparedStatement pre = conn.prepareStatement(sql);
		pre.setString(1, student_id);
		rs = pre.executeQuery();
		try {
			if(rs.next())
			{
				student = new Student();
				student.setSno(rs.getString(1));
				student.setClass_id(rs.getString(2));
				student.setSname(rs.getString(3));
				student.setSmajor(rs.getString(4));
				student.setSphone(rs.getString(5));
				student.setSqq(rs.getString(6));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return student;
	}*/
	/**
	 * 修改学生个人信息（老师修改电话 qq）
	 * @param student
	 * @return
	 */
	public boolean isUpdateCheck(Student student) {
		boolean flag = false;
		DBConnection1 con = new DBConnection1();
		String sql = "update student set smajor=?,sphone=?,sqq=? where sno=?";
		String sno = student.getSno();
		String smajor = student.getSmajor();
		String class_id = student.getClass_id();
		String phone = student.getSphone();
		String qq = student.getSqq();
		int i = con.excuteUpdate(sql, new String[] {smajor,phone,qq,sno});
		if (i!=0) {
			flag = true;
		}
		return flag;
	}
	/**
	 * 修改老师个人密码
	 * @param teacehr_id
	 * @param teacher_psd
	 * @return
	 */
	public boolean isChangePassword(String teacehr_id,String teacher_psd) {
		boolean flag = false;
		DBConnection1 conn = new DBConnection1();
		String sql = "update teacher set teacher_psd=? where teacher_id=?";
		int i = conn.excuteUpdate(sql, new String[] {teacher_psd,teacehr_id});
		if (i!=0) {
			flag = true;
		}
		return flag;
	}
	
	public boolean isPostExist(String teacher_id,String class_id,String weeknum) {
		boolean flag = false;
		DBConnection1 conn = new DBConnection1();
		//查询有无重复汇报发布
		String sql = "select * from post where teacher_id=? and class_id=? and weeknum=?";
		try {
			ResultSet rs = conn.executeQuery(sql, new String[] {teacher_id,class_id,weeknum});
			if (rs.next()) {//没有查询到时返回true
				return flag;
			}else {
				flag = true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flag;
	}
	/**
	 * 是否发布汇报成功
	 * @param post
	 * @return
	 */
	public boolean isPostSuccess(Post post) {
		boolean flag = false;
		DBConnection1 conn = new DBConnection1();
		//插入不重复汇报
		String teacher_id = post.getTeacher_id();
		String start_time = post.getStart_time();
		String end_time = post.getEnd_time();
		String class_id = post.getClass_id();
		String weeknum = post.getWeeknum();
		String sql = "insert into post values(?,?,?,?,?)";
		int i = conn.excuteUpdate(sql, new String[] {teacher_id,start_time,end_time,class_id,weeknum});
		if (i!=0) {//
			flag = true;
		}
		return flag;
	}
	
	public List<Student> getAllNoneReportStudent(List<String> allmyclass){
		DBConnection1 conn =new DBConnection1();
		ResultSet rs = null;
		//查找未交的学生学号。
		List<String> snolist = new ArrayList<>();//存储未提交学号
		String sql = "select sno from count_noreport_list where ";
		for(int i=0;i<allmyclass.size();i++){
			if(i==0)
				sql+="sno like '"+allmyclass.get(i)+"%'";
			else{
				sql+=" or sno like'"+allmyclass.get(i)+"%'";
				}
		}
		try {
			rs = conn.query(sql);
			while(rs.next())
			{
				snolist.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//获取未交学生学号姓名
		String sql1 ="select sno,class_id,sname from student where sno in(";
		for(int i=0;i<snolist.size();i++){
			if(i<snolist.size()-1)
				sql1+="'"+snolist.get(i)+"',";
			else{
				sql1+="'"+snolist.get(i)+"'";
				}
		}
		sql1+=")";
		DBConnection1 conn1 = new DBConnection1();
		ResultSet rs1 = null;
		List<Student> allnonestu = new ArrayList<>();//存储未交学生学号姓名
		try {
			rs1 = conn1.query(sql1);
			while(rs1.next())
			{	
				Student student = new Student();
				student.setSno(rs1.getString(1));
				student.setClass_id(rs1.getString(2));
				student.setSname(rs1.getString(3));
				allnonestu.add(student);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return allnonestu;
	}
}
