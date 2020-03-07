package javabean;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.DBConnection1;
import entity.Student;


public class ReportDAO {
	
	public List<Report> getResults(String sno){
	
	List<Report> list=new ArrayList();	
	DBConnection conn=new DBConnection();
	String sql="select * from report where report_id in (select report_id from stu_report where sno= '"+sno+"')";
	
	try {
		ResultSet rs=conn.query(sql);
		while(rs.next()){
			Report report=new Report();
			report.setReport_id(rs.getString(1));
			report.setReport_file(rs.getString(2));
			report.setReport_name(rs.getString(3));
			list.add(report);
		}
		conn.closeConnection();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return list;
	}
	/**
	 * 查询学生提交汇报信息
	 * @param allstudent
	 * @return
	 */
	public ResultSet getreportmes(List<Student> allstudent) {
		DBConnection1 conn = new DBConnection1();
		ResultSet rs = null;
		String sql = "SELECT stu_report.sno,report.report_file,report.report_name,stu_report.report_id FROM report,stu_report WHERE report.report_id=stu_report.report_id AND stu_report.sno IN (";
		for(int i=0;i<allstudent.size();i++) {
			if (i<allstudent.size()-1) {
				sql+="'"+ allstudent.get(i).getSno()+"',";
			}else {
				sql+="'"+ allstudent.get(i).getSno()+"'";
			}
		}
		sql+=")";
		try {
			rs = conn.query(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("未查询到数据");
		}
		return rs;
	}
	
	public boolean isInsertRemark(String note,String sno,String reportid) {
		boolean flag= false;
		DBConnection1 conn = new DBConnection1();
		String sql = "update stu_report set teacher_check=? where sno=? and report_id=?";
		int i = conn.excuteUpdate(sql, new String[] {note,sno,reportid});
		if (i>0) {
			flag = true;
		}
		return flag;
	}
}	

