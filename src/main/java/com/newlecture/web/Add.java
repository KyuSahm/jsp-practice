package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;        
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Annotation for servlet mapping
// can replace "<servlet-mapping> on web.xml"
// annotation is supported from Servlet 3.0 version 
@WebServlet("/add-result")
public class Add extends HttpServlet
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
		        
		String var1 = request.getParameter("var1");
        String var2 = request.getParameter("var2");
        
        PrintWriter out = response.getWriter();
        
        // html에서 var1이 존재하면 null값은 오지 않음.
        if (!var1.equals("") && !var2.equals(""))
        {
        	int a = Integer.parseInt(var1);
        	int b = Integer.parseInt(var2);
        	int result = a + b;        	
        	out.println("Add Calculator Result:");
            out.printf("%d + %d = %d",  a, b, result);
        }
        else
        {
        	out.println("덧셈을 수행할 수 없습니다");
        }
    }
}