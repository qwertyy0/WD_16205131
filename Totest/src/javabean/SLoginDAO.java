package javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SLoginDAO {
	public boolean isLogin(String student,String pswd) throws SQLException{
		boolean flag=false;
		DBConnection conn=new DBConnection();
		String sql="select * from student where sno='"+student+"' and spsw='"+pswd+"'";
		ResultSet rs=conn.query(sql);
		if(rs.next())
			flag=true;
		return
				flag;
	}
}
