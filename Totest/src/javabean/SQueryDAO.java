package javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SQueryDAO {
	public List<Student> getResults(String student){
		List<Student> list=new ArrayList();
		DBConnection conn=new DBConnection();
		
		String sql="select * from student where sno"+student;
		System.out.println("dao:"+sql);
		try {
			ResultSet rs=conn.query(sql);
			while(rs.next()){
				Student sinfo=new Student();
				sinfo.setSmajor(rs.getString(1));
				sinfo.setSno(rs.getString(2));
				sinfo.setSclass_id(rs.getString(3));
				sinfo.setSna(rs.getString(4));
				sinfo.setSphone(rs.getString(5));
				sinfo.setsqq(rs.getString(6));
				sinfo.setSpsw(rs.getString(7));
				list.add(sinfo);
			}
			conn.closeConnection();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}
}
