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

import dao.teacherInfo;
import entity.Student;
import entity.Teacher;
import entity.forPage;

/**
 * Servlet implementation class NoneReport
 */
@WebServlet("/NoneReport")
public class NoneReport extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoneReport() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				teacherInfo teainfo = new teacherInfo();
				HttpSession session = request.getSession();
				Teacher teacher = (Teacher) session.getAttribute("teacher");
				String teacher_id = teacher.getTeacher_id();
				try {
					List<String>  allmyclass = teacherInfo.getallclass(teacher_id);
					List<Student> allnonereport = teainfo.getAllNoneReportStudent(allmyclass);
					session.setAttribute("allmyclass", allmyclass);
					session.setAttribute("allnonereport", allnonereport);
					// session.setAttribute("allmystudent", allmystudent);
				} catch (SQLException e) {
					// TODO Auto-generated
				}
				request.getRequestDispatcher("teacher/nonereportstudent.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String allclass = request.getParameter("allclass");
		List<Student> stuclass = (List<Student>)request.getSession().getAttribute("allnonereport");//本老师所有未交学生名单
		List<Student> stuclasslist = new ArrayList<Student>();//查询的班级未交学生名单
		for(int i=0;i<stuclass.size();i++)
		{
			if (stuclass.get(i).getClass_id().equals(allclass)) {
				stuclasslist.add(stuclass.get(i));
			}
		}
		if (stuclasslist.size()==0) {
			request.setAttribute("Nullmes", "无未提交汇报学生");
			request.getRequestDispatcher("teacher/nonereportstudent.jsp").forward(request, response);

		}else {
			forPage page = new forPage(1, stuclasslist);
			request.getSession().setAttribute("page1", page);//每次请求查询一次
			request.setAttribute("oldclass", allclass);
			request.getRequestDispatcher("teacher/nonereportstudent.jsp").forward(request, response);

		}
	}

}
