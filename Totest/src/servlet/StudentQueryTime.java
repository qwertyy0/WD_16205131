package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

import javabean.DBConnection;
import javabean.Student;

/**
 * Servlet implementation class StudentQueryTime
 */
@WebServlet("/StudentQueryTime")
public class StudentQueryTime extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQueryTime() {
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
		HttpSession session2 = request.getSession(); 
		String sclass_id= (String) session2.getAttribute("sclass_id");
		String sql="select max(weeknum),post_start_time,post_end_time from post where class_id = '"+sclass_id+"' and weeknum in (select max(weeknum) from post WHERE class_id = '"+sclass_id+"') group by post_start_time,post_end_time ";
		DBConnection conn=new DBConnection();
		ResultSet rs = null;
		try {
			rs = conn.query(sql);
			rs.absolute(0);
			request.setAttribute("rs", rs);
			request.getRequestDispatcher("studentsubmitreport.jsp").forward(request, response);
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

}
