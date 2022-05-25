package util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import service.UserServiceImpl;

public class Validation {
	public static String checkStudent(String login, String password, String surname, String name, String studyYear) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("") || studyYear.equals("")) {
			return "Все поля должны быть заполнены";
		}
		
		if (!tryParse(studyYear)) {
			return "Курс должен быть числом";
		}
		
		if (Integer.parseInt(studyYear) < 0 || Integer.parseInt(studyYear) > 6) {
			return "Курс должен быть числом от 1 до 6";
		}
		
		UserServiceImpl userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "Пользователь с данным логином уже существует";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Произошла ошибка связанная с базой данных";
		}
		
		return null;
	}
	
	public static String checkInstructor(String login, String password, String surname, String name) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("")) {
			return "Все поля должны быть заполнены";
		}
		
		UserServiceImpl userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "Пользователь с данным логином уже существует";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "Произошла ошибка связанная с базой данных";
		}
		return null;
	}
	
	private static boolean tryParse(String number) {
		try {
			Integer.parseInt(number);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	public static String checkCourse(String name, String hours, String topic, String instructorId, String begin,
			String finish) {
		
		if (name.equals("") || hours.equals("") || topic.equals("") || instructorId.equals("-1") || begin.equals("") || finish.equals("")) {
			return "Все поля должны быть заполнены";
		}
		 
		LocalDate beginDate = LocalDate.parse(begin);
		LocalDate finishDate = LocalDate.parse(finish);
		
		if (beginDate.isBefore(LocalDate.now()))
			return "Дата начала курса должна быть не раньше текущего дня";
		
		if (beginDate.isAfter(finishDate))
			return "Дата начала курса должна быть раньше даты конца курса";
		
		try {
			Integer.parseInt(hours);
		} catch (NumberFormatException e) {
			return "Продолжительность курса должна быть числом";
		}
		
		return null;
	}
}
