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
		// ��� 1: �ش� ������ ����
		// ������ ���ڿ��� ���ڵ��� ������		
		response.setCharacterEncoding("UTF-8");		
		// ���������� ���� �� ����ϴ� ���ڵ��� ������(�������� ���� ���ڿ��� ��� �ؼ������� ����)		
        response.setContentType("text/html;charset=UTF-8");		
		        
        // �迭�� ���� �������� ���ؼ��� getParameterValues API ���
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
        
        out.printf("��� ���� %d",  result);
    }
}