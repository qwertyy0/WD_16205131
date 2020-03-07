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
import javax.servlet.http.HttpSession;

import javabean.DBConnection;
import javabean.Report;
import javabean.ReportDAO;
import javabean.Student;

/**
 * Servlet implementation class StudentDownloadServlet
 */
@WebServlet("/StudentDownloadServlet")
public class StudentDownloadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentDownloadServlet() {
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
//		HttpSession session_uuid = request.getSession();
//		String uuid = (String) session_uuid.getAttribute("uuid");
//		System.out.println(uuid);
//		String sql="select * from report where report_id='"+uuid+"'";
		HttpSession session = request.getSession();
		String sno=((Student) session.getAttribute("student")).getSno();
		ReportDAO music=new ReportDAO();
		List<Report> list=music.getResults(sno);
		request.setAttribute("report", list);
		request.getRequestDispatcher("studentlookreport.jsp").forward(request, response);
		
	}

}
