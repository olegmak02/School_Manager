package controller.command;

import javax.servlet.http.HttpServletRequest;

import domain.Instructor;
import domain.Student;
import domain.User;
import enums.Roles;
import service.logic.InstructorServiceImpl;
import service.logic.UserServiceImpl;
import util.SessionManager;
import util.Validation;

public class SuccessInstructorRegistration implements Command {

	private UserServiceImpl userService = new UserServiceImpl();
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
            return "/registration.jsp";
        }
		
		String login = (String)request.getParameter("login");
        String password = (String)request.getParameter("password");
        String surname = (String)request.getParameter("surname");
        String name = (String)request.getParameter("name");
		
        String validationResult = Validation.checkInstructor(login, password, surname, name);
		if (validationResult != null) { 
			request.setAttribute("message", validationResult); 
			return "/registration_instructor.jsp";
		}
		
		try {
            User createdUser = new User();
            
            createdUser.setLogin(login);
            createdUser.setPassword(password);		
            createdUser.setRole(Roles.INSTRUCTOR);
            Long id = userService .save(createdUser);
            createdUser.setId(id);
            
            Instructor createdInstructor = new Instructor();
            createdInstructor.setSurname((String)request.getParameter("surname"));
            createdInstructor.setName((String)request.getParameter("name"));
            createdInstructor.setId(id);
            instructorService.create(createdInstructor);
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/registration_instructor.jsp";
        }
		request.setAttribute("message", "ѕреподаватель успешно зарегистрирован");
        return "/registration_instructor.jsp";
	}

}
