package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.DBConnection;
import javabean.ReportDAO;

/**
 * Servlet implementation class StudentQueryCheck
 */
@WebServlet("/StudentQueryCheck")
public class StudentQueryCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQueryCheck() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String report_id = request.getParameter("report_id");
		String sql="select * from stu_report where report_id='"+report_id+"'";
		DBConnection conn=new DBConnection();
		ReportDAO report = new ReportDAO();
		ResultSet rs = null;
		try {
			rs = conn.query(sql);
			
			//System.out.println("寻找成功");
			while(rs.next()) {
			//System.out.println(rs.getInt(1));
			//System.out.println(rs.getString(2));                                                                                                                                                                                                                                                      
			//System.out.println(rs.getString(3));
			System.out.println(rs.getString(4));
			
			}
			rs.absolute(0);
			request.setAttribute("rs", rs);
			request.getRequestDispatcher("studentquerycheck.jsp").forward(request, response);
			
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
