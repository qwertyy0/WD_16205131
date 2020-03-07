package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.DBConnection;
import javabean.SQueryDAO;
import javabean.Student;

/**
 * Servlet implementation class Query
 */
@WebServlet("/Student_Query_info")
public class StudentQueryinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentQueryinfo() {
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
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession(); 
		String Ssno=((Student) session.getAttribute("student")).getSno();
		String sql="select * from student where sno='"+Ssno+"'";
		DBConnection conn=new DBConnection();
		ResultSet rs = null;
		try {
			
			rs = conn.query(sql);
			rs.absolute(0);
			request.setAttribute("rs", rs);
			request.setAttribute("str", "true");
			
			//String sql1="select * from report where report_id in (select report_id from stu_report where sno= '"+sclass_id+"')";
			//SELECT * FROM  teacher where teacher_id in (SELECT teacher_id from class where class_id = 162051)
			String sql1="SELECT * FROM  teacher where teacher_id in (SELECT teacher_id from class where class_id in(select class_id from student where sno = '"+Ssno+"'))";
			ResultSet rs1 = null;
			rs1 = conn.query(sql1);
			while(rs1.next()) {
				String teacher_name = rs1.getString(4);
				HttpSession session2 = request.getSession();
				session2.setAttribute("teacher_name", teacher_name);
			}
			

			
			request.getRequestDispatcher("studentinfo.jsp").forward(request, response);
		}catch (SQLException e) {
			// TODO: handle exception
			request.getRequestDispatcher("studentinfo.jsp").forward(request, response);
			request.setAttribute("str", "false");
			e.printStackTrace();
		}
		

	}

}
