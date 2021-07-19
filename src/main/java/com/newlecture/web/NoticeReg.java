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
		// 방법 1: tomcat의 server.xml을 수정하여 서버 설정 변경
		//        <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" />  ===>
		//      <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" URIEncoding="UTF-8" />
		// 방법 2: 웹브라우저에서 보내는 문자열을 UTF-8로 해석하라
		// request.setCharacterEncoding("UTF-8");
		// 방법 3: Filter class에서 request.setCharacterEncoding("UTF-8")를 사용
		
		// 방법 1: 해당 서블릿에 설정
		// 보내는 문자열의 인코딩을 정해줌		
		response.setCharacterEncoding("UTF-8");		
		// 웹브라우저가 읽을 때 사용하는 인코딩을 정해줌(브라우저가 받은 문자열을 어떻게 해석할지를 결정)		
        response.setContentType("text/html;charset=UTF-8");
		
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        
        PrintWriter out = response.getWriter();
        out.println(title);
        out.println(content);
    }
}