package controller.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import service.CourseServiceImpl;
import service.InstructorServiceImpl;

public class CoursesList implements Command {
	private CourseServiceImpl courseService = new CourseServiceImpl();
	@Override
	public String execute(HttpServletRequest request) {
		try {
			request.setAttribute("courses", courseService.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
			return "admin.jsp";
		}
		return "/all_courses.jsp";
	}

}
