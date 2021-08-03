<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	 
     String cntParam = request.getParameter("cnt");
     
     int cnt = 100;
     if (cntParam != null && !cntParam.equals(""))
     {
     	cnt = Integer.parseInt(cntParam);
     }     
%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	for(int i = 0;i < cnt; i++)
	{	
%>		
	안녕 Servlet~~~!!<br>
<%
	}	
%>	

</body>
</html>