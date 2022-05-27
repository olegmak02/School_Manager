<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Course" %>	
<%@ page import="domain.Result" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Courses</title>
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
   form {
   	display: block;
   }
   corner: {
   	position: absolute; 
   	right: 0;
   }
  </style>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	
	<form action="/instructor_courses" method="get">
	
        <button name="content" value="1" type="Submit">Запланированные курсы</button>
    
	
        <button name="content" value="2" type="Submit">Активные курсы</button>
    
	
        <button name="content" value="3" type="Submit">Оконченные курсы</button>

    </form>
    
    <form action="/instructor" method="get">
    	<p>
        <button type="Submit">Вернуться на главную страницу</button>
    	</p>
    </form>
    
    <table>
   		<tr><th>Название</th><th>Продолжительность</th><th>Тема</th><th>Подробнее</th></tr>
   		
  		<% 
    		List<Course> courses = (List<Course>) request.getAttribute("courses");
    		if (courses != null) {
    			for (int i = 0; i < courses.size(); i++) {
    				out.print("<tr><td>" + courses.get(i).getName() +
    					"</td><td>" + courses.get(i).getHours() +
    					"</td><td>" + courses.get(i).getTopic() +
    					"</td><td>" + "<form action=\"/result_course\" method=\"get\">" +
    					"<button name=\"choose\" value=\"" + courses.get(i).getId() + "\" type=\"Submit\">Перейти</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    		}
    	%>
  	
  	
  	</table>
</body>
</html>