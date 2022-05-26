package controller.command;

import javax.servlet.http.HttpServletRequest;

import util.SessionManager;

public class Logout implements Command {

	@Override
	public String execute(HttpServletRequest request) {
		SessionManager.deleteUserSession(request.getSession());
		return "/logout.jsp";
	}
}
