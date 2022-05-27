package controller.command.instructor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import domain.Course;
import domain.Instructor;
import domain.Result;
import domain.Student;
import service.CourseServiceImpl;
import service.ResultServiceImpl;

public class InstructorCourses implements Command {

	private CourseServiceImpl courseService = new CourseServiceImpl();
	private ResultServiceImpl resultService = new ResultServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		List<Course> courses;
		
		try {
			courses = getAllCoursesByInstructor(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/instructor_courses.jsp";
		}
		
		
		int content = 0;
		try {
			content = Integer.parseInt(request.getParameter("content"));
		} catch(Exception e) {
			
		}
		if (content == 1) {
			courses = courses.stream().filter(x -> (x.getBeginDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
		} else if (content == 2) {
			courses = courses.stream().filter(x -> (x.getBeginDate().isBefore(LocalDate.now()) && x.getFinishDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
		} else if (content == 3) {
			courses = courses.stream().filter(x -> (x.getFinishDate().isBefore(LocalDate.now()))).collect(Collectors.toList());
		}
		
		request.setAttribute("courses", courses);
		
		return "/instructor_courses.jsp";
	}
	
	private List<Course> getAllCoursesByInstructor(HttpServletRequest request) throws SQLException {
		try {
			Long id = ((Instructor) request.getSession().getAttribute("currentEntity")).getId();
			return courseService.findByInstructor(id.toString());
		} catch (SQLException e) {
			throw e;
		}
	}
}
