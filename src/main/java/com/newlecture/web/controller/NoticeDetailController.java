package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/detail")
public class NoticeDetailController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		int id = Integer.parseInt(request.getParameter("id"));
	    String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    
	    String sql = "SELECT * FROM NOTICE WHERE ID=?";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);    
		    stmt.setInt(1, id);
		    
		    ResultSet rs = stmt.executeQuery();    
		    rs.next();
		    
		    String title = rs.getString("TITLE");
		    Date regdate = rs.getDate("REGDATE");
		    String writerId = rs.getString("WRITER_ID");    
		    int hit = rs.getInt("HIT");
		    String content = rs.getString("CONTENT");
		    String files = rs.getString("FILES");
		
		    rs.close();
		    stmt.close();
		    conn.close();
		    
		    Notice notice = new Notice(id, title, regdate, writerId, hit, content, files);

		    // Step 1: request에 값을 담음.
		    request.setAttribute("notice", notice);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	 		
	    // redirect or forward
 		// redirect => 현재 작업한 내용과 상관없이 다른 페이지로 이동
 		// forward => 현재 작업한 내용을 이어감(모델을 공유)
 		// Step 2: Dispatcher를 이용해서 "spagetti.jsp"에 Forward함 
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/view/notice/detail.jsp"); // jsp 경로는 home directory에서부터 찾아가는 형식으로 지정.
 		dispatcher.forward(request,  response);
   	}
}
