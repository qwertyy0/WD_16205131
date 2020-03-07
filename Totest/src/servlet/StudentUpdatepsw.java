package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
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
 * Servlet implementation class Delete
 */
@WebServlet("/Student_Updatepsw")
public class StudentUpdatepsw extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentUpdatepsw() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession(); 
		String sno=((Student) session.getAttribute("student")).getSno();
		String Yspsw=request.getParameter("Yspsw");
		String Xspsw=request.getParameter("Xspsw");
		String sql=" update student set spsw ='"+Xspsw+"'where spsw='"+Yspsw+"' AND sno="+sno;
		DBConnection conn=new DBConnection();
		try {
			System.out.println("�޸ĳɹ�");
			request.setAttribute("str", "true");
			conn.update(sql);
			response.sendRedirect("LogoutServlet");;
		}catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
