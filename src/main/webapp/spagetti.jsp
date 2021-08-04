<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- View of MVC2 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<%
pageContext.setAttribute("aa", "hello");
pageContext.setAttribute("model", "model in page");
%>
<body>
    <%=request.getAttribute("model")%>입니다.<br>
    ${model}라고요. 우선 순위에 의해 페이지 스코프의 변수의 내용이 출력됩니다.<br>
    리스트는 ${names[0]}, ${names[1]}입니다<br>
    제목은 ${notice.title}입니다.<br>
    페이지 컨텍스트 테스트: <%=(String)pageContext.getAttribute("aa")%><br>
    페이지 컨텍스트 테스트: ${aa}<br>    
    페이지 내의 모델: ${pageScope.model}<br>
    Request 내의 모델: ${requestScope.model}<br>
    ${param.num}<br>
    ${header.accept}<br>
    num > 3: ${param.num > 3}<br>    
    num >= 3: ${param.num ge 3}<br>
    empty 연산자 테스트 ${empty param.nullvariable} = ${param.nullvariable == null || param.nullvariable == ''}<br>
    not empty 연산자 테스트 ${not empty param.nullvariable} = ${param.nullvariable != null && param.nullvariable != ''}<br>
    ${empty param.num ? '값이 비어 있습니다': param.num}<br>
    ${param.num/2}<br>
</body>
</html>