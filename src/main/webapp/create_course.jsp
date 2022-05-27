<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="domain.Instructor" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Создание курса</title>
</head>
<body>
	<form action="/create_course" method="post" accept-charset="UTF-8">
	<label>Название</label><br><input maxlength="100" type="text" name="name" />
    <p>
    	<label>Продолжительность</label><br><input maxlength="3" type="text" name="hours" reqired />
    </p>
    <p>
    	<label>Тема</label><br><input maxlength="30" type="text" name="topic" />
    </p>
    <p>
    <label>Преподаватель</label><br>
    	<p><select size="1" name="instructor_id" reqired >
    		<option value="-1"></option>
    	<%
    		List<Instructor> instructors = (List<Instructor>) request.getAttribute("instructors");
 			out.print("");
    		if (instructors != null) {
    			for(Instructor instructor: instructors) {
    				out.print("<option value=\"" + instructor.getId() + "\">" + instructor.getFullName() + "</option>");
    			}
 			}
    	%>
   		</select></p>    
    </p>
    <p>
    <label>Начало</label><br><input type="date" name="begin" reqired />     
    </p>
    <p>
    <label>Конец</label><br><input type="date" name="finish" reqired />     
    </p>
    <div>
   		<button name="created" value="true" type="Submit">Зарегистрировать</button>
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