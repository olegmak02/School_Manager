package controller.command;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import domain.Course;
import domain.Student;
import service.CourseServiceImpl;

public class OtherCourses implements Command {
	private CourseServiceImpl courseService = new CourseServiceImpl();

	@Override
	public String execute(HttpServletRequest request) {
		
		List<Course> courses;
		try {
			courses = getOtherCourses(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/other_courses.jsp";
		}
		
		courses = courses.stream().filter(x -> x.getBeginDate().isAfter(LocalDate.now())).collect(Collectors.toList());
		
		if (request.getParameter("sort") == null) {
			request.setAttribute("courses", courses);
			return "/other_courses.jsp";
		}
		
		int sort = Integer.parseInt(request.getParameter("sort"));
		
		if (sort == 1) {
			Collections.sort(courses, (Course c1, Course c2) -> {
                    return c1.getName().compareTo(c2.getName()); 
				}
			);
		} else if (sort == 2) {
			Collections.sort(courses, (Course c1,Course c2) -> {
                return c1.getHours() - c2.getHours(); 
			});
		} else if (sort == 3) {
			String topic = request.getParameter("topic");
			courses = courses.stream().filter(x -> x.getTopic().toLowerCase().contains(topic.trim().toLowerCase())).collect(Collectors.toList());
		} else if (sort == 4) {
			String instructor = request.getParameter("instructor");
			courses = courses.stream().filter(x -> x.getInstructor().getFullName().toLowerCase().contains(instructor.trim().toLowerCase())).collect(Collectors.toList());
		};
		
		request.setAttribute("courses", courses);
		
		return "/other_courses.jsp";
	}
	
	private List<Course> getOtherCourses(HttpServletRequest request) throws SQLException {
		try {
			Long id = ((Student)request.getSession().getAttribute("currentEntity")).getId();
			return courseService.getOtherByStudent(id);
		} catch (SQLException e) {
			throw e;
		}
	}
}
