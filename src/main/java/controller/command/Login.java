package controller.command;

import javax.servlet.http.HttpServletRequest;

import domain.Administrator;
import domain.Entity;
import domain.User;
import service.InstructorServiceImpl;
import service.StudentServiceImpl;
import service.UserServiceImpl;
import util.SessionManager;

public class Login implements Command {
	private UserServiceImpl userService = new UserServiceImpl();
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();
	private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getMethod().equals("GET")) {
            return "/login.jsp";
        }
		
		try {
            User user = userService.findByLoginAndPass(request.getParameter("login"),
            		request.getParameter("password"));
            if (user == null) {
            	request.setAttribute("wrong", "Логин или пароль неверный");
            	return "/login.jsp";
            }
            
            Entity entity = null;
            boolean blocked = false;
            if (user.getRole().getId() == 1) {
            	entity = instructorService.findById(user.getId());
            } else if (user.getRole().getId() == 2) {
            	entity = studentService.findById(user.getId());
            	blocked = studentService.isBlocked(user.getId());
            } else if (user.getRole().getId() == 0) {
            	entity = new Administrator();
            }
            
            if (blocked) {
            	request.setAttribute("wrong", "Вы заблокированы администратором");
            	return "login.jsp";
            }
            
            SessionManager.addUserSession(request.getSession(), user, entity);
            
            if (user.getRole().getId() == 0) {
            	return "/admin.jsp";
            } else if (user.getRole().getId() == 1) {
            	return "/instructor.jsp";
            } else if (user.getRole().getId() == 2) {
            	return "/student.jsp";
            }

        } catch (Exception ex) {
            request.setAttribute("wrong", "Username not found");
            return "/login.jsp";
        }
		
		return "/login.jsp";
	}
}
