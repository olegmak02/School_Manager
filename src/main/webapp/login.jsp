<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form action="/login" method="post" accept-charset="UTF-8">
	
		
		<div>
            <p>
                Введите логин
            </p>
        </div>
        <div>
            <input type="text" name="login"/>
        </div>
        <div>
            <p>
                Введите пароль
            </p>
        </div>
        <div>
            <div>
                <input type="text" name="password"/>
            </div>
        </div>
        
        <p>
        	<button name="Submit" value="Login" type="Submit">Войти</button>
    	</p>
    </form>
        
        <form action="/registration" method="get">
        <button style="margin-top: 10px; width: 200px" type="Submit">Зарегистрироваться</button>
    	</form>
    	
    	<div>
            <div>
                <% 
                	if (request.getAttribute("wrong") != null)
	                	out.println(request.getAttribute("wrong")); 
                %>
            </div>
        </div>
</body>
</html>
