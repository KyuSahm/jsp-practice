<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%
int x = 3;
int y = 4;
// int page = 3; // Duplicate variable error 발생, 기본적으로 생성되는 jsper code중에 page가 존재

/*  생성된 Servlet에 존재하는 내장 변수 및 객체. 중복되지 않게 사용할 필요가 있다.
    (final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
      
    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;
*/    
%>

<%=y %>

<%!
    public int sum(int a, int b)
    { 
        return a + b;
    }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
input {
	width:50px;
	height:50px;	
}
.output {
	height: 50px;
	background: #e9e9e9;
	font-size:24px;
	font-weight: bold;
	text-align: right;
	padding:  0px 5px
}
</style>
</head>
<body>
	<div>
    	<!-- <form action='calc5' method="post">-->
    	<form method="post">
	        <table>
	        	<tr>
	        		<td class="output" colspan="4">${3+5}</td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" name="operator" value="CE"/></td>
	        		<td><input type="submit" name="operator" value="C"/></td>
	        		<td><input type="submit" name="operator" value="BS"/></td>
	        		<td><input type="submit" name="operator" value="/"/></td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" name="value" value="7"/></td>
	        		<td><input type="submit" name="value" value="8"/></td>
	        		<td><input type="submit" name="value" value="9"/></td>
	        		<td><input type="submit" name="operator" value="*"/></td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" name="value" value="4"/></td>
	        		<td><input type="submit" name="value" value="5"/></td>
	        		<td><input type="submit" name="value" value="6"/></td>
	        		<td><input type="submit" name="operator" value="-"/></td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" name="value" value="1"/></td>
	        		<td><input type="submit" name="value" value="2"/></td>
	        		<td><input type="submit" name="value" value="3"/></td>
	        		<td><input type="submit" name="operator" value="+"/></td>
	        	</tr>
	        	<tr>
	        		<td><input type="submit" name="value" value="0"/></td>
	        		<td><input type="submit" name="dot" value="."/></td>
	        		<td><input type="submit" name="operator" value="="/></td>
	        	</tr>
	        </table>
		</form>
	</div>
</body>
</html>