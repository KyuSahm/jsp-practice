<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- View of MVC2 -->
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
    <%=request.getAttribute("model")%>입니다.
    ${model}라고요.
    리스트는 ${names[0]}, ${names[1]}입니다
    제목은 ${notice.title}입니다.
</body>
</html>