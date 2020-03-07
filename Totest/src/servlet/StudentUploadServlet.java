package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.Timestamp;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import javabean.DBConnection;
import javabean.Student;

@WebServlet("/Student_UploadServlet")
public class StudentUploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public StudentUploadServlet() {
        super();
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String uploadPath=getServletContext().getRealPath("/")+"imgs";          
		String uploadPath=getServletContext().getRealPath("/upload");
		System.out.println(uploadPath);
		File folder = new File(uploadPath);
		if(!folder.exists())
			folder.mkdirs();
		String message=null;
		String content=null; 
		String dtme=null;
		String fileName="";
		if(ServletFileUpload.isMultipartContent(request)){  
			DiskFileItemFactory disk=new DiskFileItemFactory();
			disk.setSizeThreshold(20*1024);                 
			disk.setRepository(disk.getRepository());       
			ServletFileUpload up=new ServletFileUpload(disk);
			int maxsize=100*1024*1024;		
			List list=null;
			try{
				list=up.parseRequest(request);              
			}
			catch(Exception e){
				e.printStackTrace();
			}
			Iterator i=list.iterator();                     
			while(i.hasNext()){
				FileItem fm=(FileItem)i.next();             
				
				if(!fm.isFormField()){
					String filePath = fm.getName();	
					
					int startIndex = filePath.lastIndexOf("\\");
					if(startIndex!=-1){						
						fileName = filePath.substring(startIndex+1);
					}else{
						fileName=filePath;
					}
					if(fm.getSize()>maxsize){
						message="file size can't be more than 2MB";
						break;
					}
					@SuppressWarnings("deprecation")
					String fileSize=new Long(fm.getSize()).toString();
					if((fileName==null)||(fileName.equals(""))&&(fileSize.equals("0"))){
						message="file can't be null";
						break;
					}
					File saveFile=new File(uploadPath,fileName);
					try{
						fm.write(saveFile);                
						message="file upload success";
					}
					catch(Exception e1){
						e1.printStackTrace();
					}
					
				}
				else{
					String foename=fm.getFieldName();     
					String con=fm.getString("gbk");       
					
					if(foename.equals("upDate")){     
						 content = con;
					}
					else if(foename.equals("upPerson")){  
						 dtme = con;
					}
				}
				
			}
		}
		
		
		//将信息插入数据库,在以下将信息插入数据库
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		HttpSession session_uuid = request.getSession();
		session_uuid.setAttribute("uuid", uuid);
		
		
		Date day=new Date(System.currentTimeMillis());
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		String now = df.format(day);   
		//文件名 fileName 
		//学号 sname
		//提交时间 dtme
		//文件编号 uuid
		//文件路径 uploadPath
		HttpSession session = request.getSession(); 
		String sno=((Student) session.getAttribute("student")).getSno();
		String sql="insert into report(report_id,report_file,report_name) values (?,?,?)";
		//第二个插入
		String sql1="insert into stu_report(sno,report_id,submit_time) values (?,?,?)";
		//查询开始结束时间
		
		DBConnection conn= new DBConnection();
		try {
			PreparedStatement pre=conn.getPreparedStatement(sql);
			pre.setString(1, uuid);
			pre.setString(2, uploadPath);
			pre.setString(3, fileName);

			pre.execute();
			
			//第二个插入
			System.out.println(now);
			
			PreparedStatement pre1=conn.getPreparedStatement(sql1);
			pre1.setString(1, sno);
			pre1.setString(2, uuid);
			pre1.setString(3, now);
	
			pre1.execute();
			conn.closeConnection();	
			
			
			
			System.out.println("提交成功");
			request.setAttribute("str", "true");
			request.getRequestDispatcher("studentsubmitreport.jsp").forward(request, response);
			
		}catch (SQLException e) {
			// TODO: handle exception
			System.out.println("提交失败");
			request.setAttribute("str", "false");
			request.getRequestDispatcher("studentsubmitreport.jsp").forward(request, response);
			e.printStackTrace();
		}
	}
		
		
		
}
