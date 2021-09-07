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
		
		// Step 1: request에 값을 담음.
	    request.setAttribute("list", list);
	    
	    // redirect or forward
 		// redirect => 현재 작업한 내용과 상관없이 다른 페이지로 이동
 		// forward => 현재 작업한 내용을 이어감(모델을 공유)
 		// Step 2: Dispatcher를 이용해서 "spagetti.jsp"에 Forward함 
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/list.jsp"); // jsp 경로는 home directory에서부터 찾아가는 형식으로 지정.
 		dispatcher.forward(request,  response);
   	}
}
