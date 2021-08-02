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
	    // 방법 1: super class에 존재하는 service() 메소드는
		// Service 메소드내에서 GET과 POST를 구분하는 방법
		if (request.getMethod().equals("GET"))
		{
			System.out.println("Get 요청이 왔습니다");
		}
		else if (request.getMethod().equals("POST"))
		{
			System.out.println("POST 요청이 왔습니다");
		}
}
else
{	
		// 방법 2: super class에 존재하는 service() 메소드는
		// "GET" 요청일 경우는 doGet(...) 함수 호출
		// "POST" 요청일 경우는 doPost(...) 함수 호출
	    // doGet()과 doPost()함수를 구현해 줘야 한다.
		super.service(request,  response);
}		
    }
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		System.out.println("doPost called. POST 요청이 왔습니다");
    }
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws javax.servlet.ServletException, java.io.IOException
	{
		System.out.println("doGet called. GET 요청이 왔습니다");
	}
}
