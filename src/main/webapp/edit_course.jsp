<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>
<%@ page import="domain.Course" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Изменение курса</title>
</head>
<body>
<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<form action="/admin">
		<button>Вернуться на главную</button>
	</form>
	<% 
		Course course = (Course) request.getAttribute("course");
	%>
	<form action="/edit_course" method="post" accept-charset="UTF-8">
	<label>Название</label><br>
	<%
		out.print("<input maxlength=\"100\" type=\"text\" name=\"name\" value=\"" + course.getName() + "\" placeholder=\"" + course.getName() + "\" />");
	 %>
	<p>
    	<label>Продолжительность</label><br>
    	<% 
    		out.print("<input maxlength=\"3\" type=\"text\" name=\"hours\" reqired value=\"" + course.getHours() + "\" placeholder=\"" + course.getHours() + "\" />");
    	%>
    </p>
    <p>
    	<label>Тема</label><br>
    	<% 
    		out.print("<input maxlength=\"30\" type=\"text\" name=\"topic\" value=\"" + course.getTopic() + "\" placeholder=\"" + course.getTopic() + "\" />");
    	%>
    </p>
    <p>
    <label>Преподаватель</label><br>
    	<p><select size="1" name="instructor_id" reqired >
    	<%	
    		out.print("<option selected value=\"" + course.getInstructor().getId() + "\">" + course.getInstructor().getFullName() + "</option>");
    	
    		List<Instructor> instructors = (List<Instructor>) request.getAttribute("instructors");
    		if (instructors != null) {
    			for (Instructor instr: instructors) {
    				out.println("<option value=\"" + instr.getId() + "\">" + instr.getFullName() + "</option>");
    			}
 			}
    	%>
   		</select></p>    
    </p>
    <p>
    <% 
    	out.print("<label>Начало</label><br><input type=\"date\" name=\"begin\" value=\"" + course.getBeginDate() + "\" placeholder=\"" + course.getBeginDate() + "\" reqired />");
    %>		
    </p>
    <p>
    <% 
    		out.print("<label>Конец</label><br><input type=\"date\" name=\"finish\" value=\"" + course.getFinishDate() + "\" placeholder=\"" + course.getFinishDate() + "\" reqired />");
    %>
    </p>
    <div>
   		<% out.print("<button name=\"edited\" value=\"" + course.getId() + "\" type=\"Submit\">Сохранить изменения</button>"); %>
    </div>
    
    <br>
    <br>
    </form>
    
    <form action="/admin" method="get">
    <p>
    	<button type="Submit">Вернуться</button>
    </p>
    </form>
    <p>
    	<%
    		String message = (String) request.getAttribute("message");
    		if (message != null)
    			out.println(message);
    	%>
    </p>
</body>
</html>