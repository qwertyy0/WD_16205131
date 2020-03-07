package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.DBConnection;
import javabean.Student;


@WebServlet("/Student_Update1_info")
public class StudentUpdate1info extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdate1info() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		String sno=((Student) session.getAttribute("student")).getSno();
		
		
		String class_id=request.getParameter("class_id");
		String sname=request.getParameter("sname");
		String smajor=request.getParameter("smajor");
		String sphone=request.getParameter("sphone");
		String sqq=request.getParameter("sqq");
		String sql="update student set sno='"+sno+"',class_id= '"+class_id+"',sname='"+sname+"',smajor = '"+smajor+"',sphone='"+sphone+"',sqq='"+sqq+"'where sno='"+sno+"'";
		

		
		DBConnection conn=new DBConnection();
		try {
			
			request.setAttribute("str", "true");
			request.getRequestDispatcher("studentchangeinfo.jsp").forward(request, response);
			conn.update(sql);
		}catch (SQLException e) {
			// TODO: handle exception
			request.setAttribute("str", "false");
			request.getRequestDispatcher("studentchangeinfo.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
}
