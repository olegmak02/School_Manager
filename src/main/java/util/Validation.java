package util;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import service.UserServiceImpl;

public class Validation {
	public static String checkStudent(String login, String password, String surname, String name, String studyYear) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("") || studyYear.equals("")) {
			return "��� ���� ������ ���� ���������";
		}
		
		if (!tryParse(studyYear)) {
			return "���� ������ ���� ������";
		}
		
		if (Integer.parseInt(studyYear) < 0 || Integer.parseInt(studyYear) > 6) {
			return "���� ������ ���� ������ �� 1 �� 6";
		}
		
		UserServiceImpl userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "������������ � ������ ������� ��� ����������";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "��������� ������ ��������� � ����� ������";
		}
		
		return null;
	}
	
	public static String checkInstructor(String login, String password, String surname, String name) {
		if (login.equals("") || password.equals("") || surname.equals("") || name.equals("")) {
			return "��� ���� ������ ���� ���������";
		}
		
		UserServiceImpl userService = new UserServiceImpl();
		
		try {
			if (userService.findByLoginAndPass(login, password) != null) {
				return "������������ � ������ ������� ��� ����������";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return "��������� ������ ��������� � ����� ������";
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
			return "��� ���� ������ ���� ���������";
		}
		 
		LocalDate beginDate = LocalDate.parse(begin);
		LocalDate finishDate = LocalDate.parse(finish);
		
		if (beginDate.isBefore(LocalDate.now()))
			return "���� ������ ����� ������ ���� �� ������ �������� ���";
		
		if (beginDate.isAfter(finishDate))
			return "���� ������ ����� ������ ���� ������ ���� ����� �����";
		
		try {
			Integer.parseInt(hours);
		} catch (NumberFormatException e) {
			return "����������������� ����� ������ ���� ������";
		}
		
		return null;
	}
}
