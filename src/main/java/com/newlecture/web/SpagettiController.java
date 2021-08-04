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
			model = "Ȧ��";
		}
		else
		{
			model = "¦��";
		}
		
		// Step 1: request�� ���� ����.
		request.setAttribute("model", model);
		
		// Step 1: request�� ���� ����.
		String[] namesStr = {"newlec", "dragon"};
		request.setAttribute("names", namesStr);
		
		// Step 1: request�� ���� ����.
		Map<String, Object> notice = new HashMap<String, Object>();
		notice.put("id",  1);
		notice.put("title",  "������ ����");
		request.setAttribute("notice", notice);
		
		// redirect or forward
		// redirect => ���� �۾��� ����� ������� �ٸ� �������� �̵�
		// forward => ���� �۾��� ������ �̾�� �ֵ��� ���� ����
		// Step 2: Dispatcher�� �̿��ؼ� "spagetti.jsp"�� Forward�� 
		RequestDispatcher dispatcher = request.getRequestDispatcher("spagetti.jsp");
		dispatcher.forward(request,  response);
	}
}
