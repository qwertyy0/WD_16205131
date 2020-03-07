package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.teacherInfo;
import entity.Student;
import entity.Teacher;

/**
 * Servlet implementation class ChangeTeacherInfo
 */
@WebServlet("/ChangeInfo")
public class ChangeInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangeInfo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//查询单个学生个人信息
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String sno = request.getParameter("student_id");//获取<a>标签提交参数
		//找出id相同的学生
		List<Student> studentlist = (List<Student>)request.getSession().getAttribute("allmystudent");
		for (int i = 0; i <studentlist.size(); i++) {
			if (studentlist.get(i).getSno().equals(sno)==true) {
				Student student = studentlist.get(i);
				request.setAttribute("selectbyid", student);
			}
		}
		request.getRequestDispatcher("teacher/teacherchangestudent.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//修改老师个人信息
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		HttpSession session = request.getSession();// 获取session对象
		Teacher teacher = new Teacher(); 
		String teacher_id = request.getParameter("teacher_id");
		String teacher_phone = request.getParameter("teacher_phone");
		String teacher_qq = request.getParameter("teacher_qq");
		teacherInfo teaInfo = new teacherInfo();
		boolean flag = teaInfo.changeTeacherInfo(teacher_id, teacher_phone, teacher_qq);
		System.out.println(flag);
		if(flag==true) {
			session.setAttribute("infomes", "修改成功");
			teacher = teacherInfo.getTeacherById(teacher_id);
			session.setAttribute("teacher", teacher);
			request.getRequestDispatcher("teacher/teacherinfo.jsp").forward(request, response);
		}else {
			session.setAttribute("infomes", "修改失败");
			request.getRequestDispatcher("teacher/teacherchangeinfo.jsp").forward(request, response);
		}
	}

}
