package adminServlet;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import adminClass.Firm;
import adminClass.ForPage;
import adminDAO.FirmInfor;

/**
 * Servlet implementation class AdminFirmServlet
 */
@WebServlet("/AdminFirmServlet")
public class AdminFirmServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdminFirmServlet() {
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
		if (operation.equals("showFirmInfo")) {
			List<Firm> firmList = FirmInfor.getAllFirm();
			int currentPage = 1;
			if (request.getParameter("currentPage") != null) {
				currentPage = Integer.parseInt(request.getParameter("currentPage"));
			}
			if (request.getSession().getAttribute("firmPages") == null) {
				ForPage<Firm> firmPages = new ForPage<>();
				firmPages.setCurrentPage(currentPage);
				firmPages.setResultList(firmList);
				firmPages.doPage(currentPage);
				request.getSession().setAttribute("firmPages", firmPages);
			} else {
				firmList = ((ForPage<Firm>) request.getSession().getAttribute("firmPages")).getResultList();
				ForPage<Firm> firmPages = new ForPage<>();
				firmPages.setCurrentPage(currentPage);
				firmPages.setResultList(firmList);
				firmPages.doPage(currentPage);
				request.getSession().setAttribute("firmPages", firmPages);
			}
			request.getRequestDispatcher("/WEB-INF/adminjsp/admincountcompany.jsp").forward(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = null;
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		type = request.getParameter("type");
		if (type.equals("search")) {
			String firm_address_sheng = request.getParameter("firm_address_sheng");
			String firm_address_shi = request.getParameter("firm_address_shi");
			System.out.println(firm_address_sheng);
			System.out.println(firm_address_shi);

			Firm firm = FirmInfor.countFirmNum(firm_address_sheng, firm_address_shi);

			request.setAttribute("type", type);
			request.setAttribute("firm", firm);
			request.getRequestDispatcher("/WEB-INF/adminjsp/admincountcompany.jsp").forward(request, response);
		}
	}

}
