package servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javabean.DBConnection;
import javabean.Student;

/**
 * Servlet implementation class Insert_firm
 */
@WebServlet("/Student_Insert_firm")
public class StudentInsertfirm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentInsertfirm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firm_id=request.getParameter("firm_id");
		String firm_name=request.getParameter("firm_name");
		String firm_address_sheng=request.getParameter("firm_address_sheng");
		String firm_address_shi=request.getParameter("firm_address_shi");
		String firm_linkman=request.getParameter("firm_linkman");
		String firm_linkman_phone=request.getParameter("firm_linkman_phone");
		
		String sql="insert into firm(firm_id,firm_name,firm_address_sheng,firm_address_shi,firm_linkman,firm_linkman_phone) values (?,?,?,?,?,?)";
		
		//第二个插入
		HttpSession session = request.getSession(); 
		String sno=((Student) session.getAttribute("student")).getSno();
		String work_start_time=request.getParameter("work_start_time");
		String work_end_time=request.getParameter("work_end_time");
		DBConnection conn=new DBConnection();
		
		String sql1="insert into stu_firm(sno,firm_id,work_start_time,work_end_time) values (?,?,?,?)";
		try {
			PreparedStatement pre=conn.getPreparedStatement(sql);
			pre.setString(1, firm_id);
			pre.setString(2, firm_name);
			pre.setString(3, firm_address_sheng);
			pre.setString(4, firm_address_shi);
			pre.setString(5, firm_linkman);
			pre.setString(6, firm_linkman_phone);
			pre.execute();
			
			//�ڶ�������
		
			PreparedStatement pre1=conn.getPreparedStatement(sql1);
			pre1.setString(1, sno);
			pre1.setString(2, firm_id);
			pre1.setString(3, work_start_time);
			pre1.setString(4, work_end_time);
			pre1.execute();
			conn.closeConnection();	
			
			System.out.println("添加成功");
			request.setAttribute("str", "true");
			request.getRequestDispatcher("studentcompanymessage.jsp").forward(request, response);
			
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("添加失败");
			request.setAttribute("str", "false");
			request.getRequestDispatcher("studentcompanymessage.jsp").forward(request, response);
			e.printStackTrace();
		}
	}

}
