package com.newlecture.web.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newlecture.web.entity.Notice;

@WebServlet("/notice/list")
public class NoticeListController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";
	    
	    String sql = "SELECT * FROM NOTICE";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url,uid, pwd);
		    Statement stmt = conn.createStatement();
		    ResultSet rs = stmt.executeQuery(sql);    
		    
		    List<Notice> list = new ArrayList<Notice>();
		    
		    while (rs.next())
		    {
		    	int id = rs.getInt("ID");
			    String title = rs.getString("TITLE");
			    Date regdate = rs.getDate("REGDATE");
			    String writerId = rs.getString("WRITER_ID");    
			    int hit = rs.getInt("HIT");
			    String content = rs.getString("CONTENT");
		        String files = rs.getString("FILES");
		        
			    Notice notice = new Notice(id, title, regdate, writerId, hit, content, files);
			    list.add(notice);
		    }		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();

		    // Step 1: request에 값을 담음.
		    request.setAttribute("list", list);
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
 		RequestDispatcher dispatcher = request.getRequestDispatcher("/notice/list.jsp"); // jsp 경로는 home directory에서부터 찾아가는 형식으로 지정.
 		dispatcher.forward(request,  response);
   	}
}
