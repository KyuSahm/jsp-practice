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
@WebServlet("/notice-reg")
public class NoticeReg extends HttpServlet
{
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException
    {
		// ��� 1: tomcat�� server.xml�� �����Ͽ� ���� ���� ����
		//        <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" />  ===>
		//      <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" URIEncoding="UTF-8" />
		// ��� 2: ������������ ������ ���ڿ��� UTF-8�� �ؼ��϶�
		// request.setCharacterEncoding("UTF-8");
		// ��� 3: Filter class���� request.setCharacterEncoding("UTF-8")�� ���
		
		// ��� 1: �ش� ������ ����
		// ������ ���ڿ��� ���ڵ��� ������		
		response.setCharacterEncoding("UTF-8");		
		// ���������� ���� �� ����ϴ� ���ڵ��� ������(�������� ���� ���ڿ��� ��� �ؼ������� ����)		
        response.setContentType("text/html;charset=UTF-8");
		
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        PrintWriter out = response.getWriter();
        out.println(title);
        out.println(content);
    }
}