package adminServlet;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import adminClass.ReadExcelByFileForXlsx;
import adminDAO.teacherInfo;
import entity.Teacher;

/**
 * Servlet implementation class InsertTeacherByXlsxServlet
 */
@WebServlet("/InsertTeacherByXlsxServlet")
public class InsertTeacherByXlsxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public InsertTeacherByXlsxServlet() {
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

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String excelFileName = request.getParameter("uploadfile");
//		System.out.println(excelFileName);

		// String uploadPath=getServletContext().getRealPath("/")+"imgs";
		// //�����ϴ��ļ��ĵ�ַ
		String uploadPath = getServletContext().getRealPath("WEB-INF//uploadExcelFolder"); // 上传到服务器的路径
		System.out.println(uploadPath);
		File folder = new File(uploadPath);
		if (!folder.exists())
			folder.mkdirs();
		String message = null;
		String fileName = "";
		// 判断是普通表单还是带文件上传的表单
		// 即<form>标记的enctype属性是否是“multipart/form-data"
		if (ServletFileUpload.isMultipartContent(request)) {
			// 配置上传参数
			DiskFileItemFactory disk = new DiskFileItemFactory();
			// 设置内存临界值
			disk.setSizeThreshold(20 * 1024);
			disk.setRepository(disk.getRepository());
			// 当项目大于临界值时使用的临时文件夹
			// System.getProperty("java.io.tmpdir");
			ServletFileUpload up = new ServletFileUpload(disk);
			int maxsize = 2 * 1024 * 1024; // 设置上传文件的大小
			List<?> list = null;
			try {
				list = up.parseRequest(request);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Iterator<?> i = list.iterator();
			while (i.hasNext()) {
				FileItem fm = (FileItem) i.next();

				// 判断FileItem类对象封装的数据是一个普通文本表单字段，
				// 还是一个文件表单字段，
				// 如果是普通表单字段则返回true，
				// 否则返回false。
				if (!fm.isFormField()) {
					// 获得文件上传字段的文件名
					String filePath = fm.getName();

					int startIndex = filePath.lastIndexOf("\\");
					if (startIndex != -1) {
						fileName = filePath.substring(startIndex + 1);
					} else {
						fileName = filePath;
					}
					if (fm.getSize() > maxsize) {
						message = "file size can't be more than 2MB";
						request.setAttribute("uploadMessage", message);
						return;
					}
					String fileSize = new Long(fm.getSize()).toString();
					if ((fileName == null) || (fileName.equals("")) && (fileSize.equals("0"))) {
						message = "file can't be null";
						request.setAttribute("uploadMessage", message);
						return;
					}
					File saveFile = new File(uploadPath, fileName);
					try {
						fm.write(saveFile);
						message = "fileUploadSuccess";
						request.setAttribute("uploadMessage", message);
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				}
			}
		}
		// 上传文件成功，将表格信息导入数据库
		if (message.equals("fileUploadSuccess")) {
			File teacherExcelFile = new File(
					getServletContext().getRealPath("WEB-INF//uploadExcelFolder//") + fileName);
			List<Teacher> list = ReadExcelByFileForXlsx.readExcelByFileForXlsx(teacherExcelFile, 1, 1, 0);
			System.out.println(list.size());
			boolean insertTeacher = teacherInfo.insertDataIntoDbByExcel(list);
			if (insertTeacher) {// 插入教师信息成功
				request.setAttribute("uploadSucceed", "uploadSucceed");
				request.setAttribute("insertSucceed", "insertSucceed");
				// 删除上传的Excel文件
				teacherExcelFile.delete();
				request.getRequestDispatcher("/WEB-INF/adminjsp/adminteacher.jsp").forward(request, response);
			} else {// 插入教师信息失败
				teacherExcelFile.delete();
				request.setAttribute("uploadSucceed", "uploadSucceed");
				request.setAttribute("insertSucceed", "insertFailed");
				request.getRequestDispatcher("/WEB-INF/adminjsp/adminteacher.jsp").forward(request, response);
			}
		} else {// 上传文件失败，返回，提示操作失败
			request.setAttribute("uploadSucceed", "uploadFailed");
			request.getRequestDispatcher("/WEB-INF/adminjsp/adminteacher.jsp").forward(request, response);
		}

	}

}
