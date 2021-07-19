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
@WebServlet("/add2")
public class Add2 extends HttpServlet
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
		        
        // 배열의 값을 가져오기 위해서는 getParameterValues API 사용
		String[] var = request.getParameterValues("vars");
        
		int result = 0;
		
		for (int i = 0; i < var.length; i++)
		{
			if (!var[i].equals(""))
			{
				int a = Integer.parseInt(var[i]);
				result += a;
			}
		}
		
        PrintWriter out = response.getWriter();
        
        out.printf("결과 값은 %d",  result);
    }
}