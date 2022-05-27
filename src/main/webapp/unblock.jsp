<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Student" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Разблокировка пользователей</title>
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
</style>
</head>
<body>
<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<form action="/admin" method="get">
  		<button type="Submit">Вернуться назад</button>
  	</form>
	
	<table>
		<tr><th>Имя</th><th>Курс</th><th>Разблокировать</th></tr>  		
		<% 
    		List<Student> students = (List<Student>) request.getAttribute("students");
    		
    			for (Student student: students) {
    				out.print("<tr><td>" + student.getFullName() +
    					"</td><td>" + student.getStudyYear() +
    					"<form action=\"/unblock\" method=\"get\"></td><td>" +
    					"<button name=\"unblock_student\" value=\"" + student.getId() + "\" type=\"Submit\">Заблокировать</button>" +
    					"</form>");
    				out.println("</td></tr>");
    			}
    	%>
  	</table>
  	
  	
</body>
</html>