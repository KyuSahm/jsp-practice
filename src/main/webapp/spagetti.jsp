<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- Controller of MVC1 -->    
<%   
    int num = 0;
	String numStr = request.getParameter("num");
	
	
	if (numStr != null && !numStr.equals(""))
	{
	    num = Integer.parseInt(numStr);
	}
	
	String model;
	
	if (num % 2 != 0) {
		model = "홀수";
	}
	else
	{
		model = "짝수";
	}	
%>

<!-- View of MVC1 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <!-- Model of MVC1 --> 
    <%=model%>입니다.
</body>
</html>