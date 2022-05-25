package controller.command;

import javax.servlet.http.HttpServletRequest;

import domain.Student;
import domain.User;
import enums.Roles;
import service.StudentServiceImpl;
import service.UserServiceImpl;
import util.SessionManager;
import util.Validation;

public class Registration implements Command {
	
	private UserServiceImpl userService = new UserServiceImpl();
	private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		if (request.getMethod().equals("GET")) {
            return "/registration.jsp";
        }
		
		String login = (String)request.getParameter("login");
        String password = (String)request.getParameter("password");
        String surname = (String)request.getParameter("surname");
        String name = (String)request.getParameter("name");
        String studyYear = (String)request.getParameter("studyYear");
		
        String validationResult = Validation.checkStudent(login, password, surname, name, studyYear);
		if (validationResult != null) { 
			request.setAttribute("errorMessage", validationResult); 
			return "/registration.jsp";
		}
		
		try {
            User createdUser = new User();
            
            createdUser.setLogin(login);
            createdUser.setPassword(password);		
            createdUser.setRole(Roles.STUDENT);
            Long id = userService.save(createdUser);
            createdUser.setId(id);
            
            Student createdStudent = new Student();
            createdStudent.setSurname((String)request.getParameter("surname"));
            createdStudent.setName((String)request.getParameter("name"));
            createdStudent.setStudyYear(Integer.parseInt(studyYear));
            createdStudent.setId(id);
            studentService.create(createdStudent);
            
            SessionManager.addUserSession(request.getSession(), createdUser, createdStudent);
            
            
        } catch (Exception e) {
            e.printStackTrace();
            return "/registration.jsp";
        }
        return "/student.jsp";
	}
	
}
