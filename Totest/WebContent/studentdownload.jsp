<%@page import="java.sql.ResultSet"%>
<%@page import="java.io.FileInputStream"%>

<%@page import="java.io.OutputStream"%>
<%@ page contentType="text/html; charset=utf-8"%>

<%
ResultSet rs=(ResultSet)request.getAttribute("rs");
	while(rs.next()) {
	String report_file=rs.getString(2);                                                                                                                                                                                                                                                      
	String report_name=rs.getString(3);
	
	System.out.print(report_name);
	System.out.print(report_file);
	
    String fileName = report_name;
    String filePath = report_file;



    out.clear();

    response.reset();

    response.setContentType("application/x-download");

    response.addHeader("Content-Disposition",    
    	      " attachment;filename=" + new String(fileName.getBytes(),"iso-8859-1"));  
    
    OutputStream os = response.getOutputStream();

    try {

        FileInputStream fis = new FileInputStream(filePath +"/" + fileName);

        try {

            byte[] buffer = new byte[100 * 1024 * 1024 ];

            for (int read; (read = fis.read(buffer)) != -1;) {

                os.write(buffer, 0, read);

            }

        } finally {

            fis.close();

        }

    } finally {
        os.close();
    }
    //
	}
	rs.absolute(0);
%>