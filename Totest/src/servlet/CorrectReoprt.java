package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javabean.ReportDAO;

/**
 * Servlet implementation class CorrectReoprt
 */
@WebServlet("/CorrectReoprt")
public class CorrectReoprt extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CorrectReoprt() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sno = request.getParameter("sno");
		String report_id = request.getParameter("report_id");
		request.setAttribute("sno", sno);
		request.setAttribute("reportid", report_id);
		request.getRequestDispatcher("teacher/teachercorrectreport.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sno = request.getParameter("needsno");
		String reportid = request.getParameter("needreportid");
		String note = request.getParameter("note");
		ReportDAO reportDAO = new ReportDAO();
		if (reportDAO.isInsertRemark(note, sno, reportid)) {
			request.setAttribute("checkmes", "批阅评价成功");
		}else {
			request.setAttribute("checkmes", "批阅评价失败");
		}
		request.getRequestDispatcher("teacher/teachercorrectreport.jsp").forward(request, response);
		
		
	}

}
