package com.newlecture.web.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;
import com.newlecture.web.service.NoticeService;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		NoticeService service = new NoticeService();
		
		List<Notice> list = service.getNoticeList();
		
		// Step 1: request�� ���� ����.
	    request.setAttribute("list", list);
	    
	    // redirect or forward
 		// redirect => ���� �۾��� ����� ������� �ٸ� �������� �̵�
 		// forward => ���� �۾��� ������ �̾(���� ����)
 		// Step 2: Dispatcher�� �̿��ؼ� "spagetti.jsp"�� Forward�� 
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp"); // jsp ��δ� home directory�������� ã�ư��� �������� ����.
 		dispatcher.forward(request,  response);
   	}
}
