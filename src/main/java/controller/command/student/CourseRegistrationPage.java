package controller.command.student;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import domain.Course;
import service.CourseServiceImpl;

public class CourseRegistrationPage implements Command{

	private CourseServiceImpl courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long courseChoose = Long.parseLong(request.getParameter("choose"));
		Course course;
		try {
			course = courseService.findById(courseChoose);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/other_courses.jsp";
		}
		
		request.setAttribute("course", course);
				
		return "/course_registration.jsp";
	}
}
