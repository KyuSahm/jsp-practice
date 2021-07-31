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
		// ��� 1: �ش� ������ ����
		// ������ ���ڿ��� ���ڵ��� ������		
		response.setCharacterEncoding("UTF-8");		
		// ���������� ���� �� ����ϴ� ���ڵ��� ������(�������� ���� ���ڿ��� ��� �ؼ������� ����)		
        response.setContentType("text/html;charset=UTF-8");		
        
        Cookie[] cookies = request.getCookies();
        String var = request.getParameter("var");
        String operator = request.getParameter("operator");
        
        PrintWriter out = response.getWriter();        
        
        int a = 0;
        
        // html���� var�� �����ϸ� null���� ���� ����.
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
        	Cookie valueCookie = new Cookie("value", String.valueOf(a));
        	Cookie opCookie = new Cookie("operator", operator);
        	
        	// Step2: ����ڰ� /calc4��� �ּҸ� ��û�� ���� �������� ���޹��� ��Ű�� ������ ���޵�.
        	valueCookie.setPath("/calc4");
        	valueCookie.setMaxAge(24*60*60); // �ʴ���, 24�ð� ����.
        	opCookie.setPath("/calc4");
        	
        	// Step1: �������� �������� ��Ű�� ������.
            response.addCookie(valueCookie);
        	response.addCookie(opCookie);
        	
        	// Page Redirect(���ϴ� �������� �̵���Ŵ)
        	response.sendRedirect("calc4.html");
        }
    }
}