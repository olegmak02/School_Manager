<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="domain.Course" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<div>
		<p>Название:
			<%
				Course course = (Course)request
				.getAttribute("course");
				if (course == null) {
					request.getRequestDispatcher("/success_registration.jsp").forward(request, response);
				} else {
					out.println(course.getName());
				}
			%>
		</p>
	</div>
	<div>
		<p>Продолжительность (часов):
			<% 
				if (course != null)
					out.println(course.getHours());
			%>
		</p>
	</div>
	<div>
		<p>Тема:
			<%
				if (course != null)
					out.println(course.getTopic());
			%>
		</p>
	</div>
	<div>
		<p>Преподаватель:
			<%
				if (course != null)
					out.println(course.getInstructor().getFullName());
			%>
		</p>
	</div>
	<div>
		<p>Начало курса:
			<%
				if (course != null)
					out.println(course.getBeginDate());
			%>
		</p>
	</div>
	
	<form action="/success_registration" method="get">
        <button type="Submit" name="course" value="${course.getId()}">Зарегистрироваться на курс</button>
    </form>
</body>
</html>