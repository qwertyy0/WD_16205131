package adminServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminClass.ForPage;
import adminDAO.teacherInfo;
import entity.Teacher;

/**
 * Servlet implementation class AdminTeacherServlet
 */
@WebServlet("/AdminTeacherServlet")
public class AdminTeacherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminTeacherServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String operation = request.getParameter("operation");
		if (operation.equals("addTeacherInfo")) {
			request.getRequestDispatcher("/WEB-INF/adminjsp/adminteacher.jsp").forward(request, response);
		} else if (operation.equals("showTeacherInfo")) {
			int currentPage = 1;
			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			List<Teacher> teacherList = new ArrayList<>();
			teacherList = null;
			if (request.getSession().getAttribute("pages") == null) {
				teacherList = teacherInfo.getAllTeacher();
				ForPage<Teacher> pages = new ForPage<>();
				pages.setCurrentPage(currentPage);
				pages.setResultList(teacherList);
				pages.doPage(currentPage);
				request.getSession().setAttribute("pages", pages);
			} else {
				teacherList = ((ForPage<Teacher>) request.getSession().getAttribute("pages")).getResultList();
				ForPage<Teacher> pages = new ForPage<>();
				pages.setCurrentPage(currentPage);
				pages.setResultList(teacherList);
				pages.doPage(currentPage);
				request.getSession().setAttribute("pages", pages);
			}
			request.getRequestDispatcher("/WEB-INF/adminjsp/showteachers.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
