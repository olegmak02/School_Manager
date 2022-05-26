<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Success registration</title>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	<div>
		<p>
			Вы успешно зарегестрировались на этот курс
		</p>
	</div>
	
	<div>
	<form action="/other_courses" method="get">
		<p>
			<button name="choose" value=null type="Submit">Посмотреть другие курсы</button>
		</p>
	</form>
	</div>
</body>
</html>