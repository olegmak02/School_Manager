package controller.command;

import javax.servlet.http.HttpServletRequest;

public class AdminPage implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		return "/admin.jsp";
	}
}
