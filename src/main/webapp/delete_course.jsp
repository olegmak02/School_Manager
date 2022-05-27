<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Course" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
   table {
    width: 100%;
    background: white;
    color: white;
    border-spacing: 1px;
   }
   td, th {
    background: maroon;
    padding: 5px;
   }
   select: {
   	display: inline-block;
   }
</style>
</head>
<body>
<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<form action="/admin">
		<button>Вернуться на главную</button>
	</form>
	<table>
   		<tr><th>Название</th><th>Продолжительность</th><th>Тема</th><th>Преподаватель</th><th>Редактировать</th></tr>
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() + 
    					"</td><td>" + courses.get(i).getInstructor().getFullName() +
    					" " + courses.get(i).getInstructor().getName() +
    					"<form action=\"/delete_course\"></td><td>" +
    					"<button name=\"deleted\" value=\"" + courses.get(i).getId() + "\" type=\"Submit\">Удалить курс</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	</table>
</body>
</html>