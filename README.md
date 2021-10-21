# JSP And Servlet
## Form Tag
- 폼 안에 있는 모든 데이터를 웹서버로 보냄.
- 웹 서버는 받은 폼 데이터를 처리하기 위해 웹 프로그램으로 넘깁니다.
- 웹 프로그램은 폼 데이터를 처리합니다.
- 처리결과에 따른 새로운 html 페이지를 웹 서버에 보냅니다.
- 웹 서버는 받은 html 페이지를 브라우저에 보냅니다
- 브라우저는 받은 html 페이지를 보여줍니다.
#### form tag 속성
- action : 폼을 전송할 서버 쪽 파일 (servlet mapping 주소)
- name : 폼을 식별하기 위한 이름 지정
- accept-charset: 폼 전송에 사용할 문자 인코딩
- target: action에서 지정한 서버쪽 파일을 현재 창이 아닌 다른 위치에 열도록 지정
- method:  폼을 서버에 전송할 http 메소드를 지정. (GET or POST)

## Get or Post
#### Get 방식
GET 방식은 URL에 폼 데이터를 추가하여 서버로 전달하는 방식입니다.
GET 방식의 HTTP 요청은 브라우저에 의해 캐시되어(cached) 저장됩니다.
GET 방식은 보통 쿼리 문자열(query string)에 포함되어 전송되므로, 길이의 제한이 있습니다.
따라서 보안상 취약점이 존재하므로, 중요한 데이터는 POST 방식을 사용하여 요청하는 것이 좋습니다.
ex) URL?name=value&name=value&...

#### Post 방식
POST 방식은 폼 데이터를 별도로 첨부하여 서버로 전달하는 방식입니다.
POST 방식의 HTTP 요청은 브라우저에 의해 캐시되지 않으므로, 브라우저 히스토리에도 남지 않습니다.
또한, POST 방식의 HTTP 요청에 의한 데이터는 쿼리 문자열과는 별도로 전송됩니다.
따라서 데이터의 길이에 대한 제한도 없으며, GET 방식보다 보안성이 높습니다

## 상태 유지를 위한 5가지 방법 (서블릿/JSP 강의 25)
#### Application
- WAS(Servlet Container)에서 appliction 공간이 서블릿간에 공유됨.
- Application 객체 : Application 범주내에서 사용. 서블릿들간에 공유함. 다른 사용자들끼리도 공유
- 서블릿 컨텍스트(Servlet Context) => Servlet들이 서로간의 문맥을 이어갈수 있는 저장공간.
- 브라우저가 바뀌어도 값이 저장되어서 전달될 수 있음.

#### session 객체
- Session 범주내에서 사용
- Session의미
  - 현재 접속한 사용자를 의미
  - 결국, 현재 접속자마다 Session ID가 달라짐.
- 동일한 브라우저 내에서는 동일한 Session으로 인식
  - 현재의 크롬브라우저에서 값을 저장 후, 새로운 크롬브라우저를 띄워도 동일한 Session
- 브라우저가 바뀌면 다른 Session으로 인식
  - 값이 저장되질 않는다
- 사용자가 브라우저를 통해 WAS서버에 접속하면, 서버는 Session ID을 부여
  - 서버의 서블릿이 하나라도 수행되면, Session ID가 부여되는 듯함
  - 응답헤더에 Session ID가 옴.
```text
Response Header: "Set-Cookie: JSESSIONID=C8E9E3CE56514A67FDD907BAF6E7D824; Path=/;HttpOnly 
```
- 동일한 종류의 브라우저를 새로 열고, 입력을 서버로 보내면, 브라우저가 요청헤더에 동일한 Session ID로 요청
```text
Request Header: "Set-Cookie: JSESSIONID=C8E9E3CE56514A67FDD907BAF6E7D824; Path=/;HttpOnly
```
- 동일한 종류의 브라우저가 모두 닫히면, Session을 닫음.
- 세션 관련 메소드
  - void setAttributes(String name, Object value)
    - 지정된 이름으로 객체를 설정
  - Object getAttributes(String name)
    - 지정한 이름의 객체를 반환
  - void invalidate()
    - 세션에서 사용되는 객체들을 바로 해제
  - void setMaxInactiveInterval(int interval)
    - 세션 타임아웃을 정수(초)로 설정
    - 사용자의 다음 요청이 올때까지 서버가 세션을 유지하는 시간
  - boolean isNew()
    - 세션이 새로 생성되었는지를 확인
  - Long getCreationTime()
    - 세션이 시작된 시간을 반환
    - 1970년 1월 1일을 시작으로 하는 밀리초
  - long getLastAccessedTime()
    - 마지막 요청시간
    - 1970년 1월 1일을 시작으로 하는 밀리초

#### Cookie
- 서버가 addCookie()를 통해 브라우저에 reponse로 Cookie를 전송
- 브라우저가 Cookie를 보유하고 있다가 WAS Server로 다시 전송
###### 사용 방법
- Step 1
  - 서버쪽에서 아래와 같이 쿠키를 생성하여 Response에 실어서 클라이언트 브라우저로 보냄
  - SetPath를 통해서 특정 주소에 접근할 경우만 브라우저에서 서버로 Cookie를 보내게 할 수 있음
```java
Cookie cookie = new Cookie("c", String.valueOf(result));
reponse.addCookie(cookie);
```
```java
// 브라우저가 "http://localhost:8080/calc4"에 접근할 경우에만 서버로 쿠키를 보내줌  
valueCookie.setPath("/calc4");
// 브라우저가 "http://localhost:8080/calc4"에 접근할 경우에만 서버로 쿠키를 보내줌
opCookie.setPath("/calc4");
// 브라우저가 모든 주소에 접근할 경우에만 서버로 쿠키를 보내줌
valueCookie.setPath("/");
// 브라우저가 모든 주소에 접근할 경우에만 서버로 쿠키를 보내줌
opCookie.setPath("/");
response.addCookie(opCookie);
response.addCookie(valueCookie);
```
- Step 2
  - 서버에서 브라우저가 보낸 쿠키값을 읽음
  - 여러개 중에 해당하는 쿠키를 찾아서 값을 읽음
```java  
Cookie[] cookies = request.getCookies();
String _c = "";
if (cookies != null)
  for (Cookie cookie: cookies)
      if ("c".equals(cookie.getName()))
          _c = cookie.getValue();
```

- Cookie의 생존주기
  - 일반적으로 브라우저가 닫히면 없어짐
  - 하지만, maxAge옵션을 사용하면, 임시인터넷 파일에 저장되어 브라우저가 닫혀도 생존가능.
```java
// 초단위, 24시간 설정.  
valueCookie.setMaxAge(24*60*60);
```
- Cookie를 지우고 싶을 때는 Server단에서 MaxAge를 0으로 만들면 됨
```java
Cookie expCookie = new Cookie("exp", exp);
      
//쿠키의 MaxAge를 0으로 하면, 쿠키를 지운다.
expCookie.setMaxAge(0);
response.addCookie(expCookie);
response.sendRedirect("calcpage");
```
#### hidden input
#### querystring
#### Application vs Session vs Cookie
###### Application
- 사용 범위: 전역 범위에서 사용하는 저장 공간
- 생명 주기: WAS가 시작해서 종료할 때까지
- 저장 위치: WAS 서버의 메모리
###### Session
- 사용 범위: 세션 범위에서 사용하는 저장 공간
- 생명 주기: 세션이 시작해서 종료할 때까지
- 저장 위치: WAS 서버의 메모리
###### Cookie
- 사용 범위: Web Browser별 지정한 path 범주 공간
- 생명 주기: Browser에 전달한 시간 부터 만료시간까지
- 저장 위치: Web Browser의 메모리 또는 파일

###### 활용 예시
- 만약 1년 정도 데이터를 저장해야 한다면 어디에? 
  - Cookie에 저장하는 것이 가능.

- 특정 URL에서만 사용하는 데이터는 어디에?
  - Cookie에 Path를 지정해서 사용하는 것이 좋다.

## 서버에서 페이지 전환기능(Redirect)
- 서버에서 응답을 할 때 현재 URL이 아닌 다른 페이지로 전환
- 원래는 사용자가 데이터를 입력 후, "+" 또는 "-" 버튼을 누른 경우   
  - "http://localhost:8080/calc4"로 이동
- "response.sendRedirect("calc4.html");"를 서블릿 코드에 입력
  - "http://localhost:8080/calc4.html"로 이동 

## 동적 페이지(Java Server Page)의 필요성
-  서버가 보낸 준 값을 화면에 표시해서 현재의 상태를 보여줄 필요

## Jasper의 역할
- Html 페이지를 PrintWriter로 생성해 주는 Servlet 코드로 바꿔준다.
```javaa
PrintWriter out = response.getWriter();

out.write("<!DOCTYPE html>");
out.write("<html>");
out.write("<head>");
out.write("<meta charset=\"UTF-8\">");
out.write("</head>");
out.write("<body>");
out.write("</body>");
out.write("</html>");
```
- Jasper을 활성화하려면, html파일의 확장자를 jsp로 바꿔주면 됨
- 사용자가 해당 페이지(예:add.jsp)를 요청하는 경우에 Servlet 코드를 생성
  - 생성된 서브릿 코드의 URL mapping은 "add.jsp"가 된다.
- 사용자가 "http://localhost:8080/calculator.jsp"를 요청하거나 새로고침하면
  - 톰켓 설치 디렉토리 아래에 위치한 폴더에 서브릿을 생성
  - "D:\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work\Catalina\localhost\ROOT\org\apache\jsp\calculator_jsp.java"

## eclipse 배포 디렉토리
- 서버항목에서 해당 tomcat서버를 Double Click하면, "Server Locations > Server Path"에 경로가 존재.

- 아래 디렉토리에 eclipse의 디렉토리 구조가 그대로 존재
  - D:\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\wtpwebapps\JSPPrj

- 아래 디렉토리에 사용자가 생성한 jsp에 대응되는 Servlet Java와 class 파일이 존재
  - D:\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work\Catalina\localhost\ROOT\org\apache\jsp\calculator_jsp.java
  - D:\Workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp1\work\Catalina\localhost\ROOT\org\apache\jsp\calculator_jsp.class

## Jsper를 이용한 코드 작성 방법(JSP 프로그래밍)
- 코드 영역
  - "<%"와 "%>"로 감싸서 처리
```jsp
<%
     int x = 3;
     int y = 4;
%>
```
- 변수를 출력하고 싶을 때
  - 생성되는 Servlet Java 파일에 "javax.servlet.jsp.JspWriter out" 변수가 존재
```jsp
// y의 값 출력
<% out.print(y) %>
// y의 값 출력
// Servlet Java 파일에 "out.print(y );" 코드가 생성
<%=y%> 
```
- 선언부(Declaration)
  - Servlet에 멤버 함수를 추가하고 싶을 때
  - calculator_jsp.java의 클래스에 멤버함수가 추가됨
```jsp  
<%!
    public int sum(int a, int b)
    { 
        return a + b;
    }
%>
```

- 초기 설정을 위한 Page 지시자
  - 페이지의 Encoding과 contentType의 종류를 설정함.
  - pageEncoding
    - 서블릿 클래스의 아래 코드에 대응됨  
```jsp  
<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
```
```java
<%
     // 보내는 문자열의 인코딩을 정해줌		
     response.setCharacterEncoding("UTF-8");		
     // 웹브라우저가 읽을 때 사용하는 인코딩을 정해줌(브라우저가 받은 문자열을 어떻게 해석할지를 결정)		
     response.setContentType("text/html;charset=UTF-8");
%>
```
## JSP 내장객체 알아보기
- HttpServletRequest
  - 정의된 메소드의 상세내용은 이미지 파일 참조
- HttpServletResponse
  - 정의된 메소드의 상세내용은 이미지 파일 참조
- javax.servlet.jsp.JspWriter
  - 정의된 메소드의 상세내용은 이미지 파일 참조
- javax.servlet.http.HttpSession
  - 정의된 메소드의 상세내용은 이미지 파일 참조
- javax.servlet.ServletContext
  - 정의된 메소드의 상세내용은 이미지 파일 참조

## MVC Model
#### MVC model1
- Controller와 View가 물리적으로 분리되지 않은 방식
- JSP 코드를 그냥 생성하면, Spagetti 코드가 생성될 수 있음
  - 입력 코드와 출력코드를 최대한 분리해서 두면 좋음
- JSP 코드를 MVC로 나눠서 코드 분리하면 Spagetti 코드를 없앨 수 있음
  - 출력 데이타만을 ``<%= %>``를 사용해서 html 코드 사이에 넣음
  - 나머지 코드는 html 상단에 위치 시킴
- Model
  - 출력 데이터
- View
  - HTML 코드
- Controller
  - 입력과 제어를 담당(자바 코드)

#### MVC model2
- Controller와 View가 물리적으로 분리된 방식
- Model
  - 별도의 Java file
- View
  - JSP와 Servlet을 통해 생성된 HTML 코드
- Controller
  - 별도의 Java file

###### Dispatcher를 집중화 하기 전 모델
- Controller마다 Dispatcher 기능이 존재.
- Controller Servlet(Controller)에서 Dispatcher 기능을 이용해서 JSP(View)로 Forward 시키면서 코드를 진행함
- JSP 페이지가 추가될 때마다 Forward를 보낼 Controller도 늘어남. (비효율적임)

###### Dispatcher를 집중화한 후의 모델
- Dispatcher Servlet는 전체적으로 하나만 둠.
- Controller들을 Servlet이 아닌 POJO 클래스 형태로 따로 둠.
- 사용자 요청이 들어오면 Dispatcher Servlet이 적절한 Controller을 찾아서 수행하도록 만듬.
- Controller는 작업 후, url-mapping 정보를 Dispatcher에게 알려주면, Dispatcher는 적절한 JSP(View)로 Forward 시키면서 진행함.

## Redirect vs Forward
#### Servlet 코드내에서의 Redirect
- 현재 작업한 내용과 상관없이 다른 페이지로 이동
#### Servlet 코드내에서의 Forward
- 현재 작업한 내용을 이어갈수 있도록 모델을 공유 
- 특정 Servlet에서 특정 JSP로 Forward 시키는 방법.
  - Dispatcher를 이용해서 HttpServletRequest에 내용을 담아서 보냄
- forward 예제 (SpagettiController.java)
```java
// Step 1: request에 값을 담음.
request.setAttribute("model", model);
  
// redirect or forward
// redirect => 현재 작업한 내용과 상관없이 다른 페이지로 이동
// forward => 현재 작업한 내용을 이어갈수 있도록 모델을 공유
// Step 2: Dispatcher를 이용해서 "spagetti.jsp"에 Forward함
RequestDispatcher dispatcher = request.getRequestDispatcher("spagetti.jsp");
dispatcher.forward(request,  response);
```
## 저장 범위
- 서버에 저장할 수 있는 4가지 종류가 있고, 클라이언트에 1개가 존재
- Application(Servlet Context) (서버)
  - 모든 페이지, 모든 세션에서 공유
- Session (서버)
  - 현재 세션내에서의 저장소
- HttpServletRequest (서버)
  - Forward 관계에서의 저장소
  - Dispatcher를 이용해서 HttpServletRequest에 실어서 보냄
- Page Context(서버)
  - JSP 내에서의 저장소
- Cookie (클라이언트 - 브라우저)
  - 클라이언트에 저장
- 저장 객체에서 값을 추출하는 순서
  - Page Context > HttpServletRequest > Session > Application(Servlet Context)
- Page Context 사용법
  - 한 JSP 페이지 내에서 아래와 같이 사용 가능
```jsp
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("aa", "hello");
%>
<body>
    페이지 컨텍스트 테스트: <%=(String)pageContext.getAttribute("aa")%>
</body>
</html>
```
## EL
#### 저장 객체에서 값을 추출해서 출력하는 표현식
- JSP(View)에서는 최대한 Java Code를 사용하지 않을 방법이 없을까?
  - 해답은 EL (Expression Language)
- JSP에 EL이라는 방식이 도입됨
- EL 코드를 사용하면?
- 사용예: 변수값
```java
// Controller(java)
request.setAttribute("cnt", 30);
```
```jsp
// View(JSP):
request.getAttribute("cnt");
${cnt} // equal with "request.getAttribute("cnt");"
```
- 사용예: List형
```java  
// Controller
List list = new ArrayList() {"1", "test", ....}
request.setAttribute("list", list);
```
```jsp
// View
((List)request.getAttribute("list")).get(0) // 0번째 Element 꺼내기
${list[0]} // 키값을 이용해서 추출. "((List)request.getAttribute("list")).get(0)"와 동일
```
- 사용예: Map형
```java
// Controller
Map<String, Object> notice = new HashMap<String, Object>();
notice.put("id",  1);
notice.put("title",  "디자인 패턴");
request.setAttribute("notice", notice);
```
```jsp
// View
((Map)request.getAttribute("notice")).get("title")
${notice.title} // "((Map)request.getAttribute("notice")).get("title")"
```
#### EL의 저장소
- Page Context,  HttpServletRequest, Session, Application(Servlet Context)에 있는 것들을 JSP에서 꺼낼 수 있음.
- 동일한 이름 "cnt"가 위 네가지의 모든 저장소에 있다면, 아래의 우선 순위로 가져 옴
  - Page Context > HttpServletRequest > Session > Application(Servlet Context)
- 동일한 이름으로 충돌나는 경우
  - Scope를 지정해서 정해진 영역에서 가져올 수 있음
```jsp  
${applicationScope.cnt}
${sessionScope.cnt}
${requestScope.cnt}
${pageScope.cnt}
```
- Page Context도 아래와 같이 사용가능
  - Page Context 사용법
    - 한 JSP 페이지 내에서 아래와 같이 사용 가능
```jsp    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
    pageContext.setAttribute("aa", "hello");
%>
<body>
    페이지 컨텍스트 테스트: <%=(String)pageContext.getAttribute("aa")%>
    페이지 컨텍스트 테스트: ${aa}
</body>
</html>
```
#### EL(Expression Language)이 값을 추출할 수 있는 다양한 저장소
- pageScope
  - Page 영역의 생명 주기에서 사용되는 저장소
- requestScope
  - Request 영역의 생명 주기에서 사용되는 저장소
- sessionScope
  - Session 영역의 생명 주기에서 사용되는 저장소
- applicationScope
  - Application 영역의 생명 주기에서 사용되는 저장소
- param
  - 파라미터 값을 저장하고 있는 저장소
```java  
// Controller:
String numStr = request.getParameter("num");
```
```jsp
// View(JSP)
${param.num}
```
- paramValues
  - 파라미터 값을 배열로 저장하고 있는 저장소
- header
  - Header 정보를 저장하고 있는 저장소
  - \${header.host}, \${header["host"]} (변수에 특수문자 또는 space가 있는 경우)
  - 브라우저의 페이지의 "Network" > "Request Headers"에 있는 정보를 추출 가능.
  - Request Headers의 Accept정보를 얻고 싶은 경우, JSP에 아래와 같이 코딩
```jsp
${header.accept}
```
- headerValues
  - Header 정보를 저장하고 있는 저장소
- cookie
  - 쿠키 정보를 저장하고 있는 저장소
- initParam
  - 컨텍스트의 초기화 파라미터를 저장하고 있는 저장소
- pageContext
  - 페이지 범위의 컨텍스트 저장소
  - 아래와 같이 객체 또는 메소드에 접근 가능
```jsp  
<%=pageContext.getRequest().getMethod()%>
${pageContext.request.method} // <%=pageContext.getRequest().getMethod()%>
```
#### EL(Expression Language) 연산자
- [], .
- ()
- not, !, empty
  - empty는 특정 변수가 NULL 또는 빈 문자열로 왔을 경우에 사용
- ?*, /, div, %, mod
- +, -
- <, >, <=, >=, lt, gt, le, ge
  - 비교 연산을 표현하는 두가지 방법이 존재
  - HTML이 "<", ">"을 많이 쓰고 있기 때문.
- ==, !=, eq, ne
- &&, and
  - 두 가지 방법 존재
- ||, or
  - 두 가지 방법 존재
- ? :
  - 삼항 연산자
```jsp
 // EL Expression                 Result
 ${1 > (4/2)}                     false    
 ${4.0 >= 3}                      false    
 ${100.0 == 100}                  true
 ${(10*10) ne 100}                false            
 ${'a' < 'b'}                     true
 ${'hip' gt 'hit'}                false    
 ${4 > 3}                         true
 ${1.2E4 + 1.4}                   12001.4
 ${3 div 4}                       0.75
 ${3/4}                           0.75
 ${10 mod 4}                      2
```
#### Eclipse IDE 설정 관련
- Eclipse IDE에서 JSP 파일내에서  추가한 내용의 Class Import하기
  - 해당 라인에서 Ctrl + Shift를 이용하면, import 문이 생성

- Eclipse IDE에서 HTML을 복사해서 JSP 파일 생성하면 한글 깨지는 문제
  - 파일 생성 후
  - 해당 JSP 파일을 Right button click해서 "properties"에서 Encoding을 UTF-8로 수정

#### JSP Project에서 외부 라이브러리관련 classpath관련
- WEB-INF/lib 디렉토리에 해당 라이브러리를 넣어주면
- Tomcat 실행 시 자동으로 classpath로 잡혀서 해당 라이브러리를 로딩
  - 사용예) ojdbc10-19.11.0.0.jar를 WEB-INF에 해당 파일을 넣어 준다.

#### JSP MVC Model 2
- Controller Servlet(Control)와 JSP(VIEW) 간의 데이터를 공유하는 방법
  - application, session, request가 가능
  - pageContext는 불가능
  - request를 사용하는 것이 가장 바람직
- 실행 순서는 Controller가 먼저 실행 후, JSP로 forward되어야 함 
  - 이유는 모델을 Controller가 공급하기 때문

#### Model 데이터를 구조화하기
- 사용자형 자료형(Entity, 개체, 개념화된 데이터)
```java
// NoticeDetailController.java - Controller Servlet
request.setAttribute("title", title);
request.setAttribute("regdate", regdate);
request.setAttribute("writerId", writerId);
request.setAttribute("hitCnt", hitCnt);
request.setAttribute("content", content);
request.setAttribute("files", files);
```
- Entity 객체를 담기
```java
public class Notice {
  private int id;
  private String title;
  ....
  public int getId() {
      return id;
  }
  ...
}
```
```java
// NoticeController.java - Controller Servlet
request.setAttribute("notice", notice);
```
```jsp
${notice.id} // 내부적으로 객체 "notice"의 getId()메소드 호출됨.
${notice.title}
```
#### JSP 내에서의 반복문
- Java Code 블록으로 가능
```jsp
<% while(rs.next) { %>
```
- Expression Language는 반복문이 존재하지 않음.
  - EL을 사용하기 위해서는 page, request, session, application 중 하나에 담아야 함
```jsp
<%
 for (Notice n:list) {
     pageContext.setAttribute("n", n);
%>
```
- JSTL Tag library로 가능

## JSP 페이지 은닉하기
- 일반적으로 사용자는 Controller에 map된 페이지에 접근하고, JSP 페이지는 Forwarding 되어 있으므로, JSP 페이지는 은닉해야 함
- 사용자가 바로 JSP 페이지에 접근하면, Controller에서 넘겨준 Model이 없기 때문에 JSP 페이지에서 에러가 발생
- "WEB-INF" 하위에 "view" 폴더를 만들어서, JSP 페이지들을 모두 옮김 
- 은닉한 JSP파일을 아래와 같이 접근하고자 하면, "HTTP 404 - 찾을수 없음" 에러가 발생
  - http://localhost:8080/WEB-INF/view/notice/list.jsp

## JSTL Tag Library
#### JSP내의 자바의 반복문 제거
- EL은 반복문을 제공하지 않음
- 반복문을 사용하기 위한 Tag가 필요
  - JSTL Tag를 이용해서 ``<forEach></forEach>`` 반복문 사용
- JSTL은 "JSP Standard Tag Library"
- JSTL tag library 다운로드 절차
  1. 구글 브라우저
  2. ``jstl download`` 검색
  3. ``Maven Repository`` 페이지로 이동
  4. ``jar`` 버튼을 클릭하여 다운로드
  5. ``WEB-INF/lib``에 복사
- ``<c:forEach var="n" items="${list}">..</c:forEach>``의 역할은 아래와 동일
  - EL을 이용해서 request에서 "list"를 꺼내와서, 루프를 돌면서 item들을 pageContext에 "n"이라는 이름으로 담아주는 역할
```java  
List<Notice> list = (List<Notice>)request.getAttribute("list");
for (Notice notice:list) {
  pageContext.setAttribute("n", notice);
          .....
} 
```
#### JSTL의 개념
- JSTL
  - JSP Standard Tag Library
  - 예전에는 사용자의 필요에 따라 Tag library를 만들어서 사용
  - 지금은 JSTL로 충분
- JSTL tag의 범주
  - Core
  - Formatting
  - Functions
  - SQL
  - XML
###### Core
- 가장 기본적인 제어의 행위를 담당
  - 예: 루프
###### Formatting
- 값을 출력할 때, 포매팅 함수 지원
  - 예: 숫자 및 날짜 포맷팅
###### Functions
- 받은 데이터를 조작하는 함수들 제공
  - 예: 문자열 쪼개기, 대문자화, 소문자화 등
###### SQL
- MVC Model 2에서는 추천하지 않음
###### XML
- MVC Model 2에서는 추천하지 않음 

#### 동작방식
- Jasper이 HTML내의 JSTL tag들을 다른 HTML Tag들과 구분짓도록 하기 위해 아래의 접두사(prefix)를 사용
```jsp
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
```
- Jasper는 ``<c:forEach ...``처럼 Tag의 접두사 "c"를 보고, URI가 "http://java.sun.com/jsp/jstl/core"인 Tag임을 인식
- 접두사 ``c``는 uri=``http://java.sun.com/jsp/jstl/core``에 대한 별칭
#### 가장 간단한 for 태그 라이브러리 만들기
- ``CREATE_SIMPLE_TAG_LIBRARY.png`` 내용 참조
- 새로운 태그를 사용하기 위해서는, Tag descriptor을 ``WEB-INF/{파일명}.tld``로 생성해줘야 함
  - Jasper가 해당 파일을 읽음
- tag descriptor을 통해서 ``for``란 이름을 가진 Tag(``<name>for</name``)가 특정 Tag class(``<tagclass>com.newlecture.web.taglib.ForTag</tagclass>``)와 연결되어 있음을 안다
- ``<uri>http://www.newlecture.com/jsp/tags/control</uri>``를 정의하는 이유
  - 만약 다른 사람이 동일한 이름의 ``for``를 생성해 놓으면, 충돌이 날 수 있기 때문에, uri를 추가해 놓음
  - 예를 들면, 자바의 클래스명에다가 패키지명을 만들듯, ``for``에 대한 URI를 만들어 두면 다른 것들과 구분이 될 수 있음
- TagSupport란 클래스를 상속하여 사용자 정의 클래스 생성
  - ``CREATE_SIMPLE_TAG_LIBRARY_2.png`` 참조
  - ``CREATE_SIMPLE_TAG_LIBRARY_3.png`` 참조
- doStartTag(), doEndTag(), doAfterBody()를 재정의 해야함
- doStartTag(): JSP내의 사용자가 정의한 ``<for>``를 만나면 호출
- doAfterBody(): JSP내의 사용자가 정의한 ``</for>``의 바로 전에서 호출
- doEndTag(): JSP내의 사용자가 정의한 ``</for>``를 만나면 호출  

#### JSTL Core 
- Tag종류는 이미지 파일 "JSTL_Core_Tags.png"파일을 참조
###### JSTL Core ``<c:forEach>`` 
- begin, end를 통해서 표시할 데이터의 인덱스를 설정가능. 
- varStatus 관련 설명
  - ``jstl_core_varStatus.png`` 파일 참조
```jsp
// 예) 0 ~ len(list) - 1
<c:forEach var="n" items="${list}" begin="0" end="3" varStatus="st">
  <tr>
    <td>${st.index + 1} / ${n.id}</td>
    <td class="title indent text-align-left"><a href="detail?id=${n.id}">${n.title}</a></td>
    <td>${n.writerId}</td>
    <td>${n.regdate}</td>
    <td>${n.hit}</td>
  </tr>
</c:forEach>
```
- ``<c:forEach>``를 이용한 페이져 구현
```jsp
// 정적으로 1 ~ 5페이지가 나오도록 구현
<ul class="-list- center">
  <c:forEach var="i" begin="0" end="4">
    <li><a class="-text- orange bold" href="?p=${i + 1 }&t=&q=" >${i + 1}</a></li>
  </c:forEach>		
</ul>
```
###### JSTL Core ``<c:set>``
- 임시변수를 선언하는 Tag
```jsp
// ${param.xxx}의 의미는 30번 참조.
 <c:set var="page" value="${(param.p == null) ? 1: param.p}"/>
 <c:set var="startNum" value="${page - (page - 1) % 5}"/>
 <ul class="-list- center">
   <c:forEach var="i" begin="0" end="4">
     <li><a class="-text- orange bold" href="?p=${startNum + i}&t=&q=" >${startNum + i}</a></li>
   </c:forEach>		
 </ul>
```

###### JSTL ``${param.xxx}``
- 예) 사용자가 아래와 같이 주소 입력 후, get으로 "name"값을 전달
  - http://127.0.0.1:8080/jstl/ifTag.jsp?name=bk 
```jsp  
// ifTag.jsp에서 jstl tag를 이용해서 받을 수 있음.
// param 은 파라미터값으로 넘어온 데이터를 뜻하고
// name 은 그 데이터중 name이라는 이름을 갖는 데이터의 값을 가져오겠다는 뜻이다.
${param.name}
```
- 결국, ``${param.name}`` 는 ``request.getParameter("name")`` 과 동일

###### JSTL Core ``<c:if test="...">``
- else문과 같은 Tag는 존재하지 않으므로, ``<c:if>``를 통해서 베타적인 구문을 생성해 줘야함.
```jsp
<c:set var="page" value="${(param.p == null) ? 1: param.p}"/>
<c:set var="startNum" value="${page - (page - 1) % 5}"/>
<!-- database에 있는 전체 데이터 개수를 알아야 함. 현재는 고정 -->
<c:set var="lastNum" value="21"/>   
<c:if test="${startNum + 5 <= lastNum}">
  <a href="?p=${startNum + 5 }&t=&q=" class="btn btn-next">다음</a>
</c:if>
<c:if test="${startNum + 5 > lastNum}">
  <span class="btn btn-next" onclick="alert('다음 페이지가 없습니다.');">다음</span>
</c:if>
```

###### JSTL Core ``<c:forTokens>``
- 파일 목록 생성하기
```jsp
<!-- String을 받아서 delimeter로 짤라서 여러개의 element를 생성해서 for loop를 돔 -->
<tr>
  <th>첨부파일</th>
    <td colspan="3" style="text-align:left;text-indent:10px">
      <c:forTokens var="fileName" items="${notice.files}" delims="," varStatus="st"> => "notice.files"의 문자열을 받아서, ","를 사용해서 여러 element로 생성. 
        <a href="${fileName}">${fileName}</a>
        <c:if test="${!st.last}">/</c:if> =>"varStatus"를 이용해서, 루프의 최종 element를 알아냄.   
      </c:forTokens>
    </td>
</tr>
```
#### JSTL format
- 관련 링크
  - ``https://www.javatpoint.com/jstl-formatting-tags``
  - ``https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=imf4&logNo=220654812087``

- format 변경을 위한 taglib를 JSP 상단에 추가
```jsp
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
```
###### formatDate
- 날짜 형식 변경
- y:년, M:월, d:일. 월을 대문자 M을 쓰는 이유는 분(minute)을 m으로 사용.
```jsp
 <td><fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${n.regdate}"/></td>
```
###### formatNumber
- 숫자 출력 형식 변경
```jsp
<!-- 기본적으로 천마다 ','를 사용 -->
<td><fmt:formatNumber value="${notice.hit}"/></td>
<!-- 네자리마다 ','를 사용. ',' 앞에 존재하는 '#'의 개수는 중요하지 않음 -->
<td><fmt:formatNumber type="number" pattern='#,####' value="${notice.hit}"/></td>
<!-- "1,2345,6777원" 출력 -->
<td><fmt:formatNumber type="number" pattern='#,####원' value="${notice.hit}"/></td>
```
```jsp
<c:set var="Amount" value="9850.14115" />  
<p> Formatted Number-1:  
<fmt:formatNumber value="${Amount}" type="currency" /></p> <!-- 출력: $9,850.14 -->
<p>Formatted Number-2:  
<fmt:formatNumber type="number" groupingUsed="true" value="${Amount}" /></p><!-- 출력: 9,850.141 -->
<p>Formatted Number-3:  
<fmt:formatNumber type="number" maxIntegerDigits="3" value="${Amount}" /></p><!-- 출력: 850.141 -->
<p>Formatted Number-4:  
<fmt:formatNumber type="number" maxFractionDigits="6" value="${Amount}" /></p><!-- 출력: 9,850.14115 -->
<p>Formatted Number-5:  
<fmt:formatNumber type="percent" maxIntegerDigits="4" value="${Amount}" /></p><!-- 출력: 5014% -->
<p>Formatted Number-6:  
<fmt:formatNumber type="number" pattern="###.###$" value="${Amount}" /></p><!-- 출력: 9850.141$ -->
```
#### JSTL:functions로 EL에서 함수 이용하기
- JSP 상단에 태그 추가
```jsp
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
```
- EL내부에 필요한 함수를 지정
  - eclipse editor에서 ``fn:``입력 후, ctrl + shift하면 함수를 찾아줌
```jsp
<!-- 파일 이름을 대문자로 바꾸기 -->
<c:forTokens var="fileName" items="${notice.files}" delims="," varStatus="st">
    <a href="${fileName}">${fn:toUpperCase(fileName)}</a>
    <c:if test="${!st.last}">/</c:if>    
</c:forTokens>
```
```jsp
<!-- "*.zip"인 경우에 글자를 폰트의 굵기와 색깔을 변경 -->
<c:forTokens var="fileName" items="${notice.files}" delims="," varStatus="st">
    <c:set var="style" value="" /> 
    <c:if test="${fn:endsWith(fileName, '.zip')}">
      <c:set var="style" value="font-weight: bold; color:red;" />
    </c:if>
    <a href="${fileName}" style="${style}">${fn:toUpperCase(fileName)}</a>
    <c:if test="${!st.last}">/</c:if>    
</c:forTokens>
```

## 기업형으로 레이어를 나누는 이유와 설명
- 분리 이유
  - 개발자가 역할 분담을 할 수 있음
- 화면(JSP)와 관련된 Controller와 내부의 서비스와 트랜잭션을 담당하는 클래스를 분리
- 서비스를 담당하는 부분을 업무 서비스(Service)와 데이터서비스(DAO)를 클래스로 다시 분리할 수 있음