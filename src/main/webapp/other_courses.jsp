<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Course" %>	
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Other courses</title>
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
	<form class="select" action="/other_courses" method="get">
	
        <button name="sort" value="1" type="Submit">Сортировать курсы по названию</button>
	
        <button name="sort" value="2" type="Submit">Сортировать курсы по продолжительности</button>
    
		<input type="text" name="topic"/>
        <button name="sort" value="3" type="Submit">Поиск по теме</button>
    
    
		<input type="text" name="instructor"/>
        <button name="sort" value="4" type="Submit">Поиск по преподавателю</button>
   	</form>
   	
   	<% 
   		if ((Long) request.getSession().getAttribute("currentRole") == 2)
   			out.print("<form action=\"/student\" method=\"get\">");
   		else if ((Long) request.getSession().getAttribute("currentRole") == 1)
   			out.print("<form action=\"/instructor\" method=\"get\">");
   	%> 
   	
   		<p>
    	<button type="Submit">Вернуться на главную страницу</button>
    	</p>
    </form>
    
    <table>
   		<tr><th>Название</th><th>Продолжительность</th><th>Тема</th><th>Преподаватель</th><th>Перейти</th></tr>
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() + 
    					"</td><td>" + courses.get(i).getInstructor().getFullName() +
    					" " + courses.get(i).getInstructor().getName() +
    					"<form action=\"/course_registration\" method=\"get\"></td><td>" +
    					"<button name=\"choose\" value=\"" + courses.get(i).getId() + "\" type=\"Submit\">Перейти к курсу</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	</table>
</body>
</html>