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
		// ��� 1: �ش� ������ ����
		// ������ ���ڿ��� ���ڵ��� ������		
		response.setCharacterEncoding("UTF-8");		
		// ���������� ���� �� ����ϴ� ���ڵ��� ������(�������� ���� ���ڿ��� ��� �ؼ������� ����)		
        response.setContentType("text/html;charset=UTF-8");		
		        
		String var1 = request.getParameter("var1");
        String var2 = request.getParameter("var2");
        
        PrintWriter out = response.getWriter();
        
        // html���� var1�� �����ϸ� null���� ���� ����.
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
        	out.println("������ ������ �� �����ϴ�");
        }
    }
}