package com.newlecture.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

// Filter로 설정하기 위한 annotation. web.xml에 filter 설정이 필요없음.
@WebFilter("/*")
public class CharacterEncodingFilter implements Filter {

	// 1. Tomcat 시작시 2번 호출
	// 2. 모든 Servlet Request들이 거쳐가는 클래스
	@Override
	public void doFilter(ServletRequest request,
			ServletResponse response,
			FilterChain chain)
			throws IOException, ServletException {
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
		request.setCharacterEncoding("UTF-8");
		
		// request를 해당 서블릿으로 Forward하고, response를 받아옴.
		// 만약, 여기서 해당 페이지로 가는 것을 막을 수도 있다.
		chain.doFilter(request, response);
	}

}
