package adminServlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import adminClass.Admin;
import adminDAO.AccountInfor;

/**
 * Servlet implementation class AccountServlet
 */
@WebServlet("/AccountServlet")
public class AccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AccountServlet() {
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
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		if (operation.equals("showInfo")) {
			System.out.println("Hi");
			admin = AccountInfor.showAdmin(admin.getAdminId());
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("/WEB-INF/adminjsp/admininfo.jsp").forward(request, response);
		} else if (operation.equals("changeInfo")) {
			admin = AccountInfor.showAdmin(admin.getAdminId());
			request.setAttribute("admin", admin);
			request.getRequestDispatcher("/WEB-INF/adminjsp/adminchangeinfo.jsp").forward(request, response);
		} else if (operation.equals("changePSW")) {
			request.getRequestDispatcher("/WEB-INF/adminjsp/changepassword.jsp").forward(request, response);
		} else if (operation.equals("changePSWNow")) {
			HttpSession session = request.getSession();
			String id = ((Admin) session.getAttribute("admin")).getAdminId();
			String mpswd = request.getParameter("mpass");
			// 检查旧密码是否输入正确
			if (AccountInfor.loginCheck(id, mpswd)) {
				String newpswd = request.getParameter("newpass");
				// 修改密码
				if (AccountInfor.changeAdminPswd(id, newpswd)) {
					// 修改成功注销登陆，返回登陆界面重新登陆
					response.sendRedirect("LogoutServlet");
				} else {// 修改失败返回修改界面并提示修改失败
					request.setAttribute("message", "修改失败！");
					request.getRequestDispatcher("/WEB-INF/adminjsp/changepassword.jsp").forward(request, response);
				}
			} else {// 输入的密码与旧密码不符，返回修改密码界面并提示输入的旧密码错误
				request.setAttribute("message", "旧密码输入错误！请重试！");
				request.getRequestDispatcher("/WEB-INF/adminjsp/changepassword.jsp").forward(request, response);
			}
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// 获取管理员用户名
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		// 获取管理员用户名
		String username = null;
		if (request.getParameter("username") != null) {
			username = request.getParameter("username");
		}
		// 获取管理员头像
		String adminlogo = null;
		if (request.getParameter("adminlogo") != null) {
			adminlogo = request.getParameter("adminlogo");
		}
		// 获取管理员联系电话
		String telephone = null;
		if (request.getParameter("telephone") != null) {
			telephone = request.getParameter("telephone");
		}
		String qq = null;
		if (request.getParameter("qq") != null) {
			qq = request.getParameter("qq");
		}
		String operation = request.getParameter("operation");
		boolean changeInfoSucceed = false;
		if (operation.equals("changeInfoNow")) {
			HttpSession session = request.getSession();
			Admin admin = (Admin) session.getAttribute("admin");
			String id = admin.getAdminId();
			changeInfoSucceed = AccountInfor.changeAdminInfo(id, username, adminlogo, telephone, qq);
			if (changeInfoSucceed) {
				request.setAttribute("message", "修改成功！");
				session.removeAttribute("admin");
				admin = AccountInfor.showAdmin(id);
				session.setAttribute("admin", admin);
				request.getRequestDispatcher("/WEB-INF/adminjsp/admininfo.jsp").forward(request, response);
			} else {
				request.setAttribute("message", "修改失败！");
				request.getRequestDispatcher("/WEB-INF/adminjsp/adminchangeinfo.jsp").forward(request, response);
			}
		}
	}

}
