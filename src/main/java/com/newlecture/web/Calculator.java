package com.newlecture.web;

import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Annotation for servlet mapping
//can replace "<servlet-mapping> on web.xml"
//annotation is supported from Servlet 3.0 version 
@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		String value = request.getParameter("value");
        String operator = request.getParameter("operator");
        String dot = request.getParameter("dot");
        
        String exp = "";
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
		
		if (operator != null && operator.equals("="))
		{
			int a = Integer.parseInt(exp.substring(0, 1));			
			int b = Integer.parseInt(exp.substring(2, 3));
			exp = String.valueOf(a+b);
			/*
			// 자바코드에서 java script를 실행할 수 있는 엔진.
			ScriptEngine engine = new ScriptEngineManager(null).getEngineByName("nashorn");
			try {
				// engine.eval => 자바스크립트 실행
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else if (operator != null && operator.equals("C"))
		{
			exp = "";
		}
		else
		{
			// 사용자는 "v" or "operator" or "dot" 세가지 종류 중에 하나만 누를 수 있다.
		    // 나머지는 null값으로 온다.
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;
		}
		
        Cookie expCookie = new Cookie("exp", exp);
        
        if (operator != null && operator.equals("C"))
		{
        	// 쿠키의 MaxAge를 0으로 하면, 쿠키를 지운다.
        	expCookie.setMaxAge(0);
		}
        // Calc5.java와 CalcPage.java를 합쳐서 Get과 Post로 분리.
        expCookie.setPath("/calculator");
        response.addCookie(expCookie);
        response.sendRedirect("calculator");  // Get 요청임
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
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
        //out.write("    	<form action='calc5' method=\"post\">");
        // 자기 페이지를 요청하는 경우는 따로 주소를 넣지 않아도 됨.
        out.write("    	<form method=\"post\">");
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
