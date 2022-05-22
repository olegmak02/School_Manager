package util;

import javax.servlet.http.HttpSession;

import domain.Entity;
import domain.User;

public class SessionManager {
	public static void addUserSession(HttpSession session, User user, Entity en) {
		session.setAttribute("currentUser", user);
		session.setAttribute("currentRole", user.getRole().getId());
		session.setAttribute("currentEntity", en);
	}
	
	public static void deleteUserSession(HttpSession session) {
		session.removeAttribute("currentUser");
		session.removeAttribute("currentRole");
		session.removeAttribute("currentEntity");
	}
}
