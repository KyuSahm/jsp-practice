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
@WebServlet("/calc5")
public class Calc5 extends HttpServlet
{
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException
    {
		// 방법 1: 해당 서블릿에 설정
		// 보내는 문자열의 인코딩을 정해줌		
		response.setCharacterEncoding("UTF-8");		
		// 웹브라우저가 읽을 때 사용하는 인코딩을 정해줌(브라우저가 받은 문자열을 어떻게 해석할지를 결정)		
        response.setContentType("text/html;charset=UTF-8");		
        
        Cookie[] cookies = request.getCookies();
        String var = request.getParameter("var");
        String operator = request.getParameter("operator");
        
        PrintWriter out = response.getWriter();        
        
        int a = 0;
        
        // html에서 var이 존재하면 null값은 오지 않음.
        if (!var.equals(""))
        {
        	a = Integer.parseInt(var);
        }
        
        if (operator.equals("="))
        {
        	int x = 0;
        	int y = a;
        	
        	for (Cookie c : cookies)
        	{
	        	if (c.getName().equals("value"))
	        	{
	        		x = Integer.parseInt(c.getValue());
	        		break;
	        	}
        	}
        	
        	String op = "";
        	for (Cookie c : cookies)
        	{
	        	if (c.getName().equals("operator"))
	        	{
	        		op = c.getValue();
	        		break;
	        	}
        	}
        		
        	if (op.equals("+"))
        	{
	        	int result = x + y;        	
	        	out.println("덧셈 결과:");
	            out.printf("%d + %d = %d",  x, y, result);
        	}
        	else
        	{
        		int result = x - y;        	
	        	out.println("뺄셈 결과:");
	            out.printf("%d - %d = %d",  x, y, result);
        	}
        }
        else
        {
        	Cookie valueCookie = new Cookie("value", String.valueOf(a));
        	Cookie opCookie = new Cookie("operator", operator);
        	
        	// Step2: 사용자가 /calc4라는 주소를 요청할 때만 브라우저가 전달받은 쿠키가 서버로 전달됨.
        	valueCookie.setPath("/calc4");
        	valueCookie.setMaxAge(24*60*60); // 초단위, 24시간 설정.
        	opCookie.setPath("/calc4");
        	
        	// Step1: 서버에서 브라우저로 쿠키를 전달함.
            response.addCookie(valueCookie);
        	response.addCookie(opCookie);
        	
        	// Page Redirect(원하는 페이지로 이동시킴)
        	response.sendRedirect("calc4.html");
        }
    }
}