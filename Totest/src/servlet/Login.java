package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javabean.SLoginDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//if(request.getParameter("selectName")!=null);{
		String select=request.getParameter("selectName");
		//if(select=="1") {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String username="";
		HttpSession session=request.getSession();
		if(request.getParameter("username")!=null){
			username=request.getParameter("username");
		}
		String password="";
		if(request.getParameter("password")!=null){
			password=request.getParameter("password");
		}
		
		SLoginDAO login=new SLoginDAO();
		
		try {
			System.out.println(login.isLogin(username, password));
			if(login.isLogin(username, password)&&select.equals("1")){
				session.setAttribute("username", username);
				request.getRequestDispatcher("studentindex.jsp").forward(request, response);
			}
			else{
				
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
	//}
	//}
}






