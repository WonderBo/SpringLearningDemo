<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Hello SpringMvc!!
	<br />
	--------First Interceptor--------
	<br />
	${requestScope.preAttr }
	<br />
	${postAttr }
	<br />
	--------Second Interceptor--------
	<br />
	${requestScope.requestAttr }
	<br />
	${sessionScope.sessionAttr }
	<br />
	${sessionScope.globalSessionAttr }
	<br />
	${postAttr2 }
	<br />
	<img alt="Cat" src="${pageContext.request.contextPath}/image/pic.jpg" width="250" height="250">
</body>
</html>