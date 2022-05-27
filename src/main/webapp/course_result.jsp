<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="domain.Student" %>    
<%@ page import="domain.Result" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Курс</title>
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
   		<% 
			List<Result> results = (List<Result>) request.getAttribute("results");
    		List<Student> students = (List<Student>) request.getAttribute("students");
    		String courseId = (String) request.getAttribute("choose");
    		if ( results == null) {
    			out.print("<div>Пока курс не начался, оценки нельзя выставлять</div>");
    		} else if (students != null) {
    			out.print("<table><tr><th>Имя</th><th>Курс</th><th>Оценка</th></tr>");
    			for (int i = 0; i < students.size(); i++) {
    				out.print("<tr><td>" + students.get(i).getFullName() +
    					"</td><td>" + students.get(i).getStudyYear() +
    					"</td><td>" + ((results.get(i) != null) ? results.get(i).getGrade() : "") + 
    					((results == null) ? "</td><td>null" : "</td><td><form action=\"/result_course\" method=\"post\"><input type=\"text\" name=\"assess\">" +
    					"<button name=\"but_choose\" value=\"" + students.get(i).getId() + ":" + courseId + "\" type=\"Submit\">Сохранить оценку</button>" +
    					"</form>"));
    				out.println("</td></tr>");
    			}
    		}
    		out.print("</table><div>" +
  						"<form action=\"/instructor_courses\" method=\"get\">" +
  			"<button>Вернуться к курсам</button></form></div>");
    	%>
  	
</body>
</html>