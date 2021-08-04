package com.newlecture.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/spagetti")
public class SpagettiController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int num = 0;
		String numStr = request.getParameter("num");		
		
		if (numStr != null && !numStr.equals(""))
		{
		    num = Integer.parseInt(numStr);
		}
		
		String model;
		
		if (num % 2 != 0) {
			model = "홀수";
		}
		else
		{
			model = "짝수";
		}
		
		// Step 1: request에 값을 담음.
		request.setAttribute("model", model);
		
		// Step 1: request에 값을 담음.
		String[] namesStr = {"newlec", "dragon"};
		request.setAttribute("names", namesStr);
		
		// Step 1: request에 값을 담음.
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id",  1);
		notice.put("title",  "디자인 패턴");
		request.setAttribute("notice", notice);
		
		// redirect or forward
		// redirect => 현재 작업한 내용과 상관없이 다른 페이지로 이동
		// forward => 현재 작업한 내용을 이어갈수 있도록 모델을 공유
		// Step 2: Dispatcher를 이용해서 "spagetti.jsp"에 Forward함 
		RequestDispatcher dispatcher = request.getRequestDispatcher("spagetti.jsp");
		dispatcher.forward(request,  response);
	}
}
