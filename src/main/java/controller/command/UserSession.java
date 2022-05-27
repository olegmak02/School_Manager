package controller.command;

import java.util.HashSet;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import domain.User;

public class UserSession {
	public static void addSession(HttpServletRequest request, User user) {
		HttpSession session = request.getSession();
        ServletContext context = request.getServletContext();
        HashSet<String> loggedUsers = (HashSet<String>) context.getAttribute("loggedUsers");
        loggedUsers.add(user.getLogin());

        context.setAttribute("loggedUsers", loggedUsers);
        session.setAttribute("user", user);
        session.setAttribute("userRole", user.getRole().name());
	}
}
