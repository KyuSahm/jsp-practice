package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation for servlet mapping
// can replace "<servlet-mapping> on web.xml"
// annotation is supported from Servlet 3.0 version 
@WebServlet("/calcpage")
public class CalcPage extends HttpServlet
{
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException
    {
		String exp = "0";
		Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie c : cookies)
	    	{
	        	if (c.getName().equals("exp"))
	        	{
	        		exp = c.getValue();
	        		break;
	        	}
	    	}
		}
		// 방법 1: 해당 서블릿에 설정
		// 보내는 문자열의 인코딩을 정해줌		
		response.setCharacterEncoding("UTF-8");		
		// 웹브라우저가 읽을 때 사용하는 인코딩을 정해줌(브라우저가 받은 문자열을 어떻게 해석할지를 결정)		
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.write("<!DOCTYPE html>");
        out.write("<html>");
        out.write("<head>");
        out.write("<meta charset=\"UTF-8\">");
        out.write("<title>Insert title here</title>");
        out.write("<style>");
        out.write("input {");
        out.write("	width:50px;");
        out.write("	height:50px;");	
        out.write("}");
        out.write(".output {");
        out.write("	height: 50px;");
        out.write("	background: #e9e9e9;");
        out.write("	font-size:24px;");
        out.write("	font-weight: bold;");
        out.write("	text-align: right;");
        out.write("	padding:  0px 5px");
        out.write("}");
        out.write("</style>");
        out.write("</head>");
        out.write("<body>");
        out.write("	<div>");
        out.write("    	<form action='calc5' method=\"post\">");
        out.write("	        <table>");
        out.write("	        	<tr>");
        out.printf("	        		<td class=\"output\" colspan=\"4\">%s</td>", exp);
        out.write("	        	</tr>");
        out.write("	        	<tr>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"CE\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"C\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"BS\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"/\"/></td>");
        out.write("	        	</tr>");
        out.write("	        	<tr>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"7\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"8\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"9\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"*\"/></td>");
        out.write("	        	</tr>");
        out.write("	        	<tr>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"4\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"5\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"6\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"-\"/></td>");
        out.write("	        	</tr>");
        out.write("	        	<tr>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"1\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"2\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"3\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"+\"/></td>");
        out.write("	        	</tr>");
        out.write("	        	<tr>");
        out.write("	        		<td><input type=\"submit\" name=\"value\" value=\"0\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"dot\" value=\".\"/></td>");
        out.write("	        		<td><input type=\"submit\" name=\"operator\" value=\"=\"/></td>");
        out.write("	        	</tr>");
        out.write("	        </table>");
        out.write("		</form>");
        out.write("	</div>");
        out.write("</body>");
        out.write("</html>");
    }
}
