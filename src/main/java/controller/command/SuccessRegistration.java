package controller.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import domain.Assignment;
import domain.Course;
import domain.Student;
import service.AssignmentServiceImpl;
import service.CourseServiceImpl;
import util.SessionManager;

public class SuccessRegistration implements Command {

	private AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long id = Long.parseLong(request.getParameter("course"));
		Student student = (Student)request.getSession().getAttribute("currentEntity");
		if (assignmentService.checkAssign(student.getId(), id) == 1) {
			return "/course.jsp";
		};
		CourseServiceImpl courseService = new CourseServiceImpl();
		Course course;
		try {
			course = courseService.findById(id);
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("choose", id);
			
			return "/course.jsp";
		}
		
		Assignment assignment = new Assignment();
		assignment.setCourse(course);
		assignment.setStudent(student);
		
		try {
			assignment.setId(assignmentService.save(assignment));
		} catch (SQLException e) {
			e.printStackTrace();
			return "/course.jsp";
		}
		
		return "/success_registration.jsp";
	}
	
}
