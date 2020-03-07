package adminServlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminClass.Admin;
import adminClass.ForPage;
import adminDAO.studentInfo;
import javabean.Student;

/**
 * Servlet implementation class AdminStudentServlet
 */
@WebServlet("/AdminStudentServlet")
public class AdminStudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminStudentServlet() {
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
		String operation = null;
		operation = request.getParameter("operation");
		if (operation.equals("unsubmitStudent")) {
			// 每次查询未提交的学生信息都要对数据库进行更新
			List<Student> unstudentList = studentInfo.getUnsubmitStudent();
			String adminId = ((Admin) request.getSession().getAttribute("admin")).getAdminId();
			// 存在未提交报告的学生
			int sun = unstudentList.size();
			if (sun > 0) {
				boolean insertSuccees = studentInfo.insertUnsubmitStudent(adminId, unstudentList);
				if (insertSuccees) {// 如果更新数据库成功
					int currentPage = 1;
					if (request.getParameter("currentPage") != null) {
						currentPage = Integer.parseInt(request.getParameter("currentPage"));
					}
					if (request.getSession().getAttribute("unPages") == null) {
						ForPage<Student> unPages = new ForPage<>();
						unPages.setResultList(unstudentList);
						unPages.setCurrentPage(currentPage);
						unPages.doPage(currentPage);
						request.getSession().setAttribute("unPages", unPages);
					} else {
						unstudentList = ((ForPage<Student>) request.getSession().getAttribute("unPages")).getResultList();
						ForPage<Student> unPages = new ForPage<>();
						unPages.setCurrentPage(currentPage);
						unPages.setResultList(unstudentList);
						unPages.doPage(currentPage);
						request.getSession().setAttribute("unPages", unPages);
					}
					request.setAttribute("updatemessage", "updateSucceed");
				} else {// 如果更新数据库失败
					request.setAttribute("updatemessage", "updateFailed");
				}
				request.setAttribute("message", "studentExist");
			} else {// 不存在未提交报告的学生
				request.setAttribute("message", "studentNotExist");
			}
			request.getRequestDispatcher("/WEB-INF/adminjsp/adminstuunsubmit.jsp").forward(request, response);
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
