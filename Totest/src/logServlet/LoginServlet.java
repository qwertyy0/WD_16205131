package logServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import adminClass.Admin;
import adminDAO.AccountInfor;
import adminDAO.studentInfo;
import adminDAO.teacherInfo;
import entity.Teacher;
import javabean.Student;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
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
		HttpSession session = request.getSession();
		if (session.getAttribute("admin") == null && session.getAttribute("teacher") == null
				&& session.getAttribute("student") == null) {
			request.getRequestDispatcher("/WEB-INF/adminjsp/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		String verificationCode = (String) session.getAttribute("verificationCode");// 获取验证码序列
		String checkcode = request.getParameter("code");// 获取提交的验证码

		if (verificationCode.equals(checkcode)) {
			String username = request.getParameter("username");
			String pswd = request.getParameter("password");
			String loginType = request.getParameter("loginType");

			boolean loginSuccess;
			if (loginType.equals("0")) {// 管理员登陆
				loginSuccess = AccountInfor.loginCheck(username, pswd);
				if (loginSuccess) {
					Admin admin = AccountInfor.getAdmin(username, pswd);
					session.setAttribute("admin", admin);
					request.getRequestDispatcher("/WEB-INF/adminjsp/adminindex.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "账号或密码错误！");
					request.getRequestDispatcher("/WEB-INF/adminjsp/login.jsp").forward(request, response);
				}
			} else if (loginType.equals("1")) {// 教师登陆
				loginSuccess = teacherInfo.loginCheck(username, pswd);
				if (loginSuccess) {
					Teacher teacher = teacherInfo.getTeacher(username, pswd);
					session.setAttribute("teacher", teacher);
					request.getRequestDispatcher("/WEB-INF/teacherjsp/teacherindex.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "账号或密码错误！");
					request.getRequestDispatcher("/WEB-INF/adminjsp/login.jsp").forward(request, response);
				}
			} else if (loginType.equals("2")) {// 学生登陆
				loginSuccess = studentInfo.loginCheck(username, pswd);
				if (loginSuccess) {
					Student student = studentInfo.getStudent(username, pswd);
					session.setAttribute("student", student);
					request.getRequestDispatcher("studentindex.jsp").forward(request, response);
				} else {
					request.setAttribute("message", "账号或密码错误！");
					request.getRequestDispatcher("/WEB-INF/adminjsp/login.jsp").forward(request, response);
				}
			}
		} else {
			request.setAttribute("message", "验证码错误！");
			request.getRequestDispatcher("/WEB-INF/adminjsp/login.jsp").forward(request, response);
		}
	}
}