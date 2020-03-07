package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.teacherInfo;
import entity.Post;
import entity.Teacher;

/**
 * Servlet implementation class TeacherPost
 */
@WebServlet("/TeacherPost")
public class TeacherPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TeacherPost() {
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
		HttpSession session =request.getSession();
		String teacher_id = ((Teacher)session.getAttribute("teacher")).getTeacher_id();
		String class_id = request.getParameter("allclass");
		String weeknum = request.getParameter("weeknum");
		String start_time = request.getParameter("start_time").replace("T", " ");
		String end_time = (request.getParameter("end_time")).replace("T", " ");
		//System.out.println(start_time);
		teacherInfo teainfo = new teacherInfo();
		Post post = new Post();
		post.setTeacher_id(teacher_id);post.setStart_time(start_time);post.setEnd_time(end_time);post.setClass_id(class_id);post.setWeeknum(weeknum);
		if (teainfo.isPostExist(teacher_id, class_id, weeknum)) {
			boolean flag=teainfo.isPostSuccess(post);
			if (flag) {
				request.setAttribute("postmes", "发布成功");
				request.getRequestDispatcher("teacher/teacherpost.jsp").forward(request, response);
			}else {
				request.setAttribute("postmes", "发布失败");
				request.getRequestDispatcher("teacher/teacherpost.jsp").forward(request, response);

			}
		}else {
			request.setAttribute("postmes", "已存在汇报");
			request.getRequestDispatcher("teacher/teacherpost.jsp").forward(request, response);

		}
		
	}

}
