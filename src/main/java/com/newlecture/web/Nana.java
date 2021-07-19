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
@WebServlet("/hi")
public class Nana extends HttpServlet
{
	@Override
    public void service(HttpServletRequest request, HttpServletResponse response)
           throws IOException, ServletException
    {
		// 웹브라우저에서 보내는 문자열을 UTF-8로 해석하라
		request.setCharacterEncoding("UTF-8");		
		// 보내는 문자열의 인코딩을 정해줌		
		response.setCharacterEncoding("UTF-8");		
		// 웹브라우저가 읽을 때 사용하는 인코딩을 정해줌(브라우저가 받은 문자열을 어떻게 해석할지를 결정)		
	    response.setContentType("text/html;charset=UTF-8");	
	 
        String cntParam = request.getParameter("cnt");
        
        int cnt = 100;
        if (cntParam != null && !cntParam.equals(""))
        {
        	cnt = Integer.parseInt(cntParam);
        }
        
        PrintWriter out = response.getWriter();
        for (int i = 0; i < cnt; i++)
        {
        	out.println((i + 1) + ": 안녕 Servlet~~~!!<br>");
        }
        /*
        OutputStream os = response.getOutputStream();
        // second option for buffer flush
        PrintStream out = new PrintStream(os, true);
        out.println("Hello Servlet!!");*/
    }
}