package com.newlecture.web;

import java.io.IOException;

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
		String value = request.getParameter("value");
        String operator = request.getParameter("operator");
        String dot = request.getParameter("dot");
        
        String exp = "";
        Cookie[] cookies = request.getCookies();
		if (cookies != null)
		{
			for (Cookie c : cookies)
	    	{
	        	if (c.getName().equals("exp"))
	        	{
	        		exp = c.getValue();
	        		break;
	        	}
	    	}
		}
		
		if (operator != null && operator.equals("="))
		{
			int a = Integer.parseInt(exp.substring(0, 1));			
			int b = Integer.parseInt(exp.substring(2, 3));
			exp = String.valueOf(a+b);
			/*
			// �ڹ��ڵ忡�� java script�� ������ �� �ִ� ����.
			ScriptEngine engine = new ScriptEngineManager(null).getEngineByName("nashorn");
			try {
				// engine.eval => �ڹٽ�ũ��Ʈ ����
				exp = String.valueOf(engine.eval(exp));
			} catch (ScriptException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}*/
		}
		else if (operator != null && operator.equals("C"))
		{
			exp = "";
		}
		else
		{
			// ����ڴ� "v" or "operator" or "dot" ������ ���� �߿� �ϳ��� ���� �� �ִ�.
		    // �������� null������ �´�.
			exp += (value == null) ? "" : value;
			exp += (operator == null) ? "" : operator;
			exp += (dot == null) ? "" : dot;
		}
		
        Cookie expCookie = new Cookie("exp", exp);
        
        if (operator != null && operator.equals("C"))
		{
        	// ��Ű�� MaxAge�� 0���� �ϸ�, ��Ű�� �����.
        	expCookie.setMaxAge(0);
		}
        response.addCookie(expCookie);
        response.sendRedirect("calcpage");
    }
}