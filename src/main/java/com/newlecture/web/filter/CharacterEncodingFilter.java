package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// Filter�� �����ϱ� ���� annotation. web.xml�� filter ������ �ʿ����.
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	// 1. Tomcat ���۽� 2�� ȣ��
	// 2. ��� Servlet Request���� ���İ��� Ŭ����
	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
		// ��� 1: tomcat�� server.xml�� �����Ͽ� ���� ���� ����
		//        <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" />  ===>
		//      <Connector port="8080" protocol="HTTP/1.1"
        //         connectionTimeout="20000"
        //         redirectPort="8443" URIEncoding="UTF-8" />
		// ��� 2: ������������ ������ ���ڿ��� UTF-8�� �ؼ��϶�
		// request.setCharacterEncoding("UTF-8");
		// ��� 3: Filter class���� request.setCharacterEncoding("UTF-8")�� ���		
		request.setCharacterEncoding("UTF-8");
		
		// request�� �ش� �������� Forward�ϰ�, response�� �޾ƿ�.
		// ����, ���⼭ �ش� �������� ���� ���� ���� ���� �ִ�.
		chain.doFilter(request, response);
	}

}
