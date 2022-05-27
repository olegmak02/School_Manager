package controller.command;

import javax.servlet.http.HttpServletRequest;

public class StudentPage implements Command {

	@Override
	public String execute(HttpServletRequest request) {
        return "/student.jsp";
	}
	
}
