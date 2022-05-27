<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Регистрация инструктора</title>
</head>
<body>
	<form action="/success_instructor_registration" method="post" accept-charset="UTF-8">
	<label>Фамилия</label><br><input type="text" name="surname" />
        
    <p>
    	<label>Имя</label><br><input type="text" name="name" />
      
    </p>
    <p>
    	<label>Логин</label><br><input type="text" name="login" />
    </p>
    <p>
    <label>Пароль</label><br><input type="text" name="password" />
            
    </p>
    
    <div>
   		<button type="Submit">Зарегистрировать</button>
   
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