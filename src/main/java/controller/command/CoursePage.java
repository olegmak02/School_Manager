package controller.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import domain.Course;
import service.logic.CourseServiceImpl;
import util.SessionManager;

public class CoursePage implements Command{

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
				
		return "/course.jsp";
	}
}
