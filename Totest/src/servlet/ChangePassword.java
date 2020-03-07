package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.teacherInfo;
import entity.Teacher;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/ChangePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() {
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
		request.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();
		String teacher_id = ((Teacher)(session.getAttribute("teacher"))).getTeacher_id();
		String newteacher_psd = (String)request.getParameter("renewpass");
		String pwd = ((Teacher)session.getAttribute("teacher")).getTeacher_psd();
		if ( (request.getParameter("mpass")).equals(pwd) ){
			teacherInfo teainfo = new teacherInfo();
			boolean flag = teainfo.isChangePassword(teacher_id, newteacher_psd);
			if (flag) {
				//修改成功即退出重新登录
				response.sendRedirect("LogoutServlet");
			}else {
				// 修改失败返回修改界面并提示修改失败
				request.setAttribute("message", "修改失败！");
				request.getRequestDispatcher("/teacher/changepassword.jsp").forward(request, response);

			}
		}else {
			// 输入的密码与旧密码不符，返回修改密码界面并提示输入的旧密码错误
			request.setAttribute("message", "旧密码输入错误！请重试！");
			request.getRequestDispatcher("/teacher/changepassword.jsp").forward(request, response);

		}
	}

}
