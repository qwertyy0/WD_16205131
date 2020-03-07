package servlet;

import java.io.IOException;
import java.sql.ResultSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entity.Student;
import javabean.ReportDAO;

/**
 * Servlet implementation class SelectAllFile
 */
@WebServlet("/SelectAllFile")
public class SelectAllFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAllFile() {
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
		List<Student> allstudent = (List<Student>)session.getAttribute("allmystudent");
		ReportDAO reportDao = new  ReportDAO();
		ResultSet rSet = reportDao.getreportmes(allstudent);
		if (rSet!=null) {
			request.getSession().setAttribute("allstudentreport", rSet);
			request.getRequestDispatcher("teacher/teachercorrectreport.jsp").forward(request, response);
		}else {
			request.setAttribute("selectError", "查询到学生提交汇报");
			request.getRequestDispatcher("teacher/teachercorrectreport.jsp").forward(request, response);
		}
		
		
	}

}
