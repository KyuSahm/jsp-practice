package com.newlecture.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;        
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// Annotation for servlet mapping
// can replace "<servlet-mapping> on web.xml"
// annotation is supported from Servlet 3.0 version 
@WebServlet("/calc3")
public class Calc3 extends HttpServlet
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
        
        String var = request.getParameter("var");
        String operator = request.getParameter("operator");
        
        PrintWriter out = response.getWriter();        
        
        int a = 0;
        
        // html에서 var이 존재하면 null값은 오지 않음.
        if (!var.equals(""))
        {
        	a = Integer.parseInt(var);
        }
        
        //ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();
        if (operator.equals("="))
        {
        	// Servlet Context에서 저장된 값을 Attribute 이름을 통해 찾아옴.
        	//String op = (String)application.getAttribute("op");
        	String op = (String)session.getAttribute("op");
        	//int x = (Integer)application.getAttribute("value");        	
        	int x = (Integer)session.getAttribute("value");
        	int y = a;
        	
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
        	// 향후에 사용될 것을 대비해 Servlet Context에 저장함.
            // Attribute "이름문자열"에 Object형태로 저장한다.            
            //application.setAttribute("value",  a);
            //application.setAttribute("op",  operator);            
        	session.setAttribute("value",  a);
            session.setAttribute("op",  operator);
        }
    }
}