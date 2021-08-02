package com.newlecture.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//Annotation for servlet mapping
//can replace "<servlet-mapping> on web.xml"
//annotation is supported from Servlet 3.0 version 
@WebServlet("/calculator")
public class Calculator extends HttpServlet {
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException
    {
if (false)
{	
	    // ��� 1: super class�� �����ϴ� service() �޼ҵ��
		// Service �޼ҵ峻���� GET�� POST�� �����ϴ� ���
		if (request.getMethod().equals("GET"))
		{
			System.out.println("Get ��û�� �Խ��ϴ�");
		}
		else if (request.getMethod().equals("POST"))
		{
			System.out.println("POST ��û�� �Խ��ϴ�");
		}
}
else
{	
		// ��� 2: super class�� �����ϴ� service() �޼ҵ��
		// "GET" ��û�� ���� doGet(...) �Լ� ȣ��
		// "POST" ��û�� ���� doPost(...) �Լ� ȣ��
	    // doGet()�� doPost()�Լ��� ������ ��� �Ѵ�.
		super.service(request,  response);
}		
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		System.out.println("doPost called. POST ��û�� �Խ��ϴ�");
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		System.out.println("doGet called. GET ��û�� �Խ��ϴ�");
	}
}
