<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Course" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Courses</title>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<form action="/my_courses" method="get">
	<p>
        <button name="content" value="1" type="Submit">Запланированные курсы</button>
    </p>
    </form>
    
    <form action="/my_courses" method="get">
	<p>
        <button name="content" value="2" type="Submit">Активные курсы</button>
    </p>
    </form>
    
    <form action="/my_courses" method="get">
	<p>
        <button name="content" value="3" type="Submit">Оконченные курсы</button>
    </p>
    </form>
    
    <% 
    	List<Course> courses = (List<Course>) request.getAttribute("courses");
    	if (courses != null) {
    		for (Course course: courses) {
    			out.print("Название: " + course.getName() +
    					"\t\tПродолжительность (часов): " + course.getHours() +
    					"\t\tТема: " + course.getTopic() + 
    					"\t\tПреподаватель: " + course.getInstructor().getSurname() +
    					" " + course.getInstructor().getName());
    			out.println("<br>");
    		}
    
    
    	}
    %>
    
</body>
</html>