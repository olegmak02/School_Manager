<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
</head>
<body>
	<form action="/registration" method="post" accept-charset="UTF-8">
	<label>Фамилия</label><br><input type="text" name="surname" />
        
    <p>
    	<label>Имя</label><br><input type="text" name="name" />
      
    </p>
    <p>
    	<label>Курс</label><br><input type="text" name="studyYear" /> 
    </p> 
    <p>
    	<label>Логин</label><br><input type="text" name="login" />
    </p>
    <p>
    <label>Пароль</label><br><input type="text" name="password" />
            
    </p>
    
    <div>
    	<p>
    		<button type="Submit">Зарегистрироваться</button>
    	</p>
    </div>
    <br>
    <br>
    </form>
    
    
    
    <form action="/login" method="get">
    <p>
    		<button type="Submit">Отмена</button>
    </p>
    </form>
    <p>
    	<%
    		String message = (String) request.getAttribute("errorMessage");
    		if (message != null)
    			out.println(message);
    	%>
    </p>
</body>
</html>
