package com.newlecture.web.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.newlecture.web.entity.Notice;

public class NoticeService {
	public List<Notice> getNoticeList() {
		return getNoticeList("title", "", 1);
	}
	
	public List<Notice> getNoticeList(int page) {		
		return getNoticeList("title", "", page);
	}
	
	/*
	 * String field => TITLE, WRITER_ID
	 * String query => 
	 */
	public List<Notice> getNoticeList(String field, String query, int page) {
		List<Notice> list = new ArrayList<Notice>();
		
		String sql =
				"SELECT * " + 
				 " FROM (SELECT ROWNUM ROWSEQ, A.* "+
				         " FROM (SELECT * " + 
				                 " FROM NOTICE "+
				                " WHERE " + field + " LIKE ? "+
				                " ORDER BY REGDATE DESC) A ) "+
				" WHERE ROWSEQ BETWEEN ? and ?";
		// 1, 11, 21, 31 -> an = 1 + (n - 1) * 5
		// 5, 10, 15 -> page * 5
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setString(1, "%" + query + "%");
		    stmt.setInt(2, 1 + (page - 1) * 5);
		    stmt.setInt(3, page * 5);
		    ResultSet rs = stmt.executeQuery();
		    
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
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return list;
	}
	
	public int getNoticeCount() {
		return getNoticeCount("title", "");
	}
	
	public int getNoticeCount(String field, String query) {
		int count = 0;		
		String sql =
				"SELECT COUNT(*) CNT" + 
				 " FROM NOTICE "+
			 	" WHERE " + field + " LIKE ? ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setString(1, "%" + query + "%");
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next())
		    {
		    	count = rs.getInt("CNT");
		    }		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return count;
	}
	
	public Notice getNotice(int id) {
		Notice notice = null;
		
		String sql =
				"SELECT * " +
				 " FROM NOTICE "+
				" WHERE ID = ?";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next())
		    {
		    	int nId = rs.getInt("ID");
			    String title = rs.getString("TITLE");
			    Date regdate = rs.getDate("REGDATE");
			    String writerId = rs.getString("WRITER_ID");    
			    int hit = rs.getInt("HIT");
			    String content = rs.getString("CONTENT");
		        String files = rs.getString("FILES");
		        
			    notice = new Notice(nId, title, regdate, writerId, hit, content, files);
		    }		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return notice;
	}
	
	public Notice getPrevNotice(int id) {
		Notice notice = null;
		
		String sql =
				"SELECT * " +
		         " FROM (SELECT ROWNUM ROWSEQ, B.* "+
		                 " FROM (SELECT * "+
		                         " FROM NOTICE A "+
		                        " WHERE REGDATE < (SELECT REGDATE "+ 
		                                           " FROM NOTICE "+
		                                          " WHERE ID = ?) "+
		                        " ORDER BY REGDATE DESC) B) "+
		        " WHERE ROWSEQ = 1 ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next())
		    {
		    	int nId = rs.getInt("ID");
			    String title = rs.getString("TITLE");
			    Date regdate = rs.getDate("REGDATE");
			    String writerId = rs.getString("WRITER_ID");    
			    int hit = rs.getInt("HIT");
			    String content = rs.getString("CONTENT");
		        String files = rs.getString("FILES");
		        
			    notice = new Notice(nId, title, regdate, writerId, hit, content, files);
		    }		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return notice;
	}
	
	public Notice getNextNotice(int id) {
		Notice notice = null;
		
		String sql = "SELECT * " +
				      " FROM (SELECT ROWNUM ROWSEQ, B.* "+
				              " FROM (SELECT * "+
				                      " FROM NOTICE A "+
				                     " WHERE REGDATE > (SELECT REGDATE "+ 
				                                       " FROM NOTICE "+
				                                      " WHERE ID = ?) "+
				                     " ORDER BY REGDATE ASC) B) "+
				     " WHERE ROWSEQ = 1 ";
		
		String url = "jdbc:oracle:thin:@localhost:1521/xepdb1";
	    String uid = "NEWLEC";
	    String pwd = "ehalthf93";
	    String driver = "oracle.jdbc.driver.OracleDriver";   
	    
	    try {
			Class.forName(driver);
			Connection conn = DriverManager.getConnection(url, uid, pwd);
		    PreparedStatement stmt = conn.prepareStatement(sql);
		    
		    stmt.setInt(1, id);
		    ResultSet rs = stmt.executeQuery();
		    
		    if (rs.next())
		    {
		    	int nId = rs.getInt("ID");
			    String title = rs.getString("TITLE");
			    Date regdate = rs.getDate("REGDATE");
			    String writerId = rs.getString("WRITER_ID");    
			    int hit = rs.getInt("HIT");
			    String content = rs.getString("CONTENT");
		        String files = rs.getString("FILES");
		        
			    notice = new Notice(nId, title, regdate, writerId, hit, content, files);
		    }		    
		    
		    rs.close();
		    stmt.close();
		    conn.close();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
		return notice;
	}
}
