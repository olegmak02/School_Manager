package controller.command.instructor;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;

public class InstructorPage implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		
		return "/instructor.jsp";
	}
}
