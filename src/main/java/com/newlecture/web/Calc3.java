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
		// ��� 1: �ش� ������ ����
		// ������ ���ڿ��� ���ڵ��� ������		
		response.setCharacterEncoding("UTF-8");		
		// ���������� ���� �� ����ϴ� ���ڵ��� ������(�������� ���� ���ڿ��� ��� �ؼ������� ����)		
        response.setContentType("text/html;charset=UTF-8");		
        
        String var = request.getParameter("var");
        String operator = request.getParameter("operator");
        
        PrintWriter out = response.getWriter();        
        
        int a = 0;
        
        // html���� var�� �����ϸ� null���� ���� ����.
        if (!var.equals(""))
        {
        	a = Integer.parseInt(var);
        }
        
        //ServletContext application = request.getServletContext();
        HttpSession session = request.getSession();
        if (operator.equals("="))
        {
        	// Servlet Context���� ����� ���� Attribute �̸��� ���� ã�ƿ�.
        	//String op = (String)application.getAttribute("op");
        	String op = (String)session.getAttribute("op");
        	//int x = (Integer)application.getAttribute("value");        	
        	int x = (Integer)session.getAttribute("value");
        	int y = a;
        	
        	if (op.equals("+"))
        	{
	        	int result = x + y;        	
	        	out.println("���� ���:");
	            out.printf("%d + %d = %d",  x, y, result);
        	}
        	else
        	{
        		int result = x - y;        	
	        	out.println("���� ���:");
	            out.printf("%d - %d = %d",  x, y, result);
        	}
        }
        else
        {
        	// ���Ŀ� ���� ���� ����� Servlet Context�� ������.
            // Attribute "�̸����ڿ�"�� Object���·� �����Ѵ�.            
            //application.setAttribute("value",  a);
            //application.setAttribute("op",  operator);            
        	session.setAttribute("value",  a);
            session.setAttribute("op",  operator);
        }
    }
}