<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>添加课程</title>
</head>
<body>
	<form action="<%=path %>/course/save" method="post">
	<!-- 表单各组件的name需要与bean的各项属性一一对应（相同） -->
		<div class="form_list">
			<label class="lable_title">课程ID</label><input class="form_input"
				type="text" name="courseId" />
		</div>
		<div class="form_list">
			<label class="lable_title">课程名</label><input
				class="form_input" type="text" name="courseName" />
		</div>
		<div class="form_list">
			<label class="lable_title">课程级别</label>
			<input type="radio" name="level" value=1>&nbsp;&nbsp;一&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="level" value=2>&nbsp;&nbsp;二&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="level" value=3>&nbsp;&nbsp;三&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="level" value=4>&nbsp;&nbsp;四&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<input type="radio" name="level" value=5>&nbsp;&nbsp;五&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		</div>
		<br />
		<div class="form_list" id="subbot">
			<input type="submit" class="submit"
				value="&nbsp;&nbsp;注&nbsp;&nbsp;册&nbsp;&nbsp;">
		</div>
	</form>
</body>
</html>