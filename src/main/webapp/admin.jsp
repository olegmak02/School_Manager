<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form style="position: absolute; right: 0;" action="/logout" method="get">
        <button type="Submit">Выйти </button>
	</form>
	
	<p>Вы находитесь на странице администратора</p>
	<div>
		<p>Имя: Администратор</p>
	</div>
	
	<form action="/block" method="get">
		<p>
			<button type="Submit">Заблокировать студента</button>
		</p>
	</form>
	<form action="/unblock" method="get">
		<p>
			<button type="Submit">Разблокировать студента</button>
		</p>
	</form>
	<form action="/create_course" method="get">
		<p>
			<button type="Submit">Создать курс</button>
		</p>
	</form>
	<form action="/all_courses" method="get">
		<p>
			<button type="Submit">Изменить курс</button>
		</p>
	</form>
	<form action="/delete_course" method="get">
		<p>
			<button type="Submit">Удалить курс</button>
		</p>
	</form>
	<form action="/registration_instructor" method="get">
		
			<button type="Submit">Зарегистрировать преподавателя</button>
		
	</form>
</body>
</html>
