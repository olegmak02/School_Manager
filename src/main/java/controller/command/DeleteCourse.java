package controller.command;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;

import service.logic.CourseServiceImpl;
import service.logic.InstructorServiceImpl;

public class DeleteCourse implements Command {
	private CourseServiceImpl courseService = new CourseServiceImpl();
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		if (request.getParameter("deleted") != null) {
			try {
				Long choose = Long.parseLong((String)request.getParameter("deleted"));
				courseService.delete(choose);
			} catch (SQLException e) {
				e.printStackTrace();
				return "Не удалось удалить курс из-за проблем с базой данных";
			}
		}
		
		try {
			request.setAttribute("courses", courseService.findAll());
			request.setAttribute("instructors", instructorService.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
			return "/admin.jsp";
		}
		return "/delete_course.jsp";
	}

}
