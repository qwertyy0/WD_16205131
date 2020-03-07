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

import javabean.DBConnection;
import javabean.Student;

/**
 * Servlet implementation class Update
 */
@WebServlet("/Student_Update_info")
public class StudentUpdateinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdateinfo() {
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
					request.getRequestDispatcher("studentchangeinfo.jsp").forward(request, response);
				}catch (SQLException e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
}
