package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.DBConnection;
import javabean.Report;
import javabean.ReportDAO;

/**
 * Servlet implementation class Update1
 */
@WebServlet("/StudentDownloadServlet1")
public class StudentDownloadServlet1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDownloadServlet1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String report_name = request.getParameter("report_name");
		String sql="select * from report where report_name='"+report_name+"'";
		DBConnection conn=new DBConnection();
		ReportDAO report = new ReportDAO();
		ResultSet rs = null;
		try {
			rs = conn.query(sql);
			
			//System.out.println("寻找成功");
			//while(rs.next()) {
			//System.out.println(rs.getInt(1));
			//System.out.println(rs.getString(2));                                                                                                                                                                                                                                                      
			//System.out.println(rs.getString(3));
			//System.out.println(rs.getString(4));
			//System.out.println(rs.getInt(5));
			//}
			rs.absolute(0);
			request.setAttribute("rs", rs);
			request.getRequestDispatcher("studentdownload.jsp").forward(request, response);
			//conn.query(sql);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
	}
}
