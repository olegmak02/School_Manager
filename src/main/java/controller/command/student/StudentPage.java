package controller.command.student;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class StudentPage implements Command {

	@Override
	public String execute(HttpServletRequest request) {
        return "/student.jsp";
	}
}
