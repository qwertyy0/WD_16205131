package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mysql.cj.Session;

import dao.teacherInfo;
import entity.Student;
import entity.Teacher;
import entity.forPage;

/**
 * Servlet implementation class SelectMineStudent
 */
@WebServlet("/SelectMineStudent")
public class SelectMineStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//处理get请求的servlet逻辑
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();// 获取session对象
		Teacher teacher = (Teacher) session.getAttribute("teacher");
		String teacher_id = teacher.getTeacher_id();
		try {
			List<String>  allmyclass = teacherInfo.getallclass(teacher_id);
			List<Student> allmystudent = teacherInfo.getmyclassstudent(teacher_id);
			session.setAttribute("allmyclass", allmyclass);
			session.setAttribute("allmystudent", allmystudent);
			// session.setAttribute("allmystudent", allmystudent);
			forPage page = new forPage(1, allmystudent);
			session.setAttribute("page", page);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("teacher/teacherselectstudent.jsp").forward(request, response);

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		//处理post请求的servlet逻辑
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();// 获取session对象

		String snoclass = (String)request.getParameter("allclass");
		
		List<Student> listmid = (List<Student>)session.getAttribute("allmystudent");
		List<Student> listlast = new ArrayList<Student>();
		try {
			for(int i=0;i<listmid.size();i++)
			{
				if (listmid.get(i).getClass_id().equals(snoclass)==true) {
					listlast.add(listmid.get(i));
					
				}	
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		//使用分页工具类
		forPage page = new forPage(1, listlast);
		request.getSession().setAttribute("page", page);
		request.getRequestDispatcher("teacher/selectbyclass_id.jsp").forward(request, response);
	}

}
