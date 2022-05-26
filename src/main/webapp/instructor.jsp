<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>    

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Student Page</title>
<style>
	form {
		display: inline-block;
	}
</style>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<p>Вы находитесь на странице преподавателя</p>
	<div>
		<p>Имя:
			<% 
				Instructor instructor = (Instructor)request
				.getSession()
				.getAttribute("currentEntity");
				out.println(instructor.getFullName());
			%>
		</p>
	</div>
	
	<form action="/instructor_courses" method="get">
		<p>
			<button type="Submit">Посмотреть мои курсы</button>
		</p>
	</form>
	<form action="/other_courses" method="get">
		<p>
			<button type="Submit">Посмотреть другие курсы</button>
		</p>
	</form>
</body>
</html>
