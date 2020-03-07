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
 * Servlet implementation class UpStuInfo
 */
@WebServlet("/UpStuInfo")
public class UpStuInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpStuInfo() {
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
		String sno = request.getParameter("sno");
		String smajor = request.getParameter("smajor");
		String sphone = request.getParameter("sphone");
		String sqq  =request.getParameter("sqq");
		Student stu = new Student();
		stu.setSno(sno);stu.setSmajor(smajor);stu.setSphone(sphone);stu.setSqq(sqq);
		teacherInfo tea = new teacherInfo();
		HttpSession session = request.getSession();
		if (tea.isUpdateCheck(stu)) {
			request.setAttribute("errormes", "修改成功");
			try {
				List<Student> allmystudentnew = teacherInfo.getmyclassstudent(((Teacher)session.getAttribute("teacher")).getTeacher_id());
				session.setAttribute("allmystudent", allmystudentnew);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("未更新新的班级学生表");
			}
			
		}else {
			request.setAttribute("errormes", "修改失败");
		}
		request.getRequestDispatcher("teacher/teacherselectstudent.jsp").forward(request, response);
	}

}
