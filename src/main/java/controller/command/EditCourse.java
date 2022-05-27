package controller.command;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import domain.Course;
import domain.Instructor;
import service.CourseServiceImpl;
import service.InstructorServiceImpl;
import util.Validation;

public class EditCourse implements Command {
	private CourseServiceImpl courseService = new CourseServiceImpl();
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		Long choose;
		try {
			choose = Long.parseLong(request.getParameter("choose"));
		} catch (NumberFormatException e) {
			choose = Long.parseLong(request.getParameter("edited"));
		}
		if(request.getParameter("edited") != null) {
			String name = request.getParameter("name");
			String hours = request.getParameter("hours");
			String topic = request.getParameter("topic");
			String instructorId = request.getParameter("instructor_id");
			String begin = request.getParameter("begin");
			String finish = request.getParameter("finish");
			String message = Validation.checkCourse(name, hours, topic, instructorId, begin, finish);
			if (message != null) {
				request.setAttribute("message", message);
			} else {
				Course createdCourse = new Course();
				createdCourse.setId(choose);
				createdCourse.setName(name);
				createdCourse.setHours(Integer.parseInt(hours));
				createdCourse.setTopic(topic);
				Instructor instructor = new Instructor();
				instructor.setId(Long.parseLong(instructorId));
				createdCourse.setInstructor(instructor);
				createdCourse.setBeginDate(LocalDate.parse(begin));
				createdCourse.setFinishDate(LocalDate.parse(finish));
				try {
					courseService.save(createdCourse);
				} catch (SQLException e) {
					e.printStackTrace();
					return "Ќе удалось создать курс из-за проблем с базой данных";
				}
				request.setAttribute("message", " урс успешно изменен");
			};
		}
		
		try {
			Course course = courseService.findById(choose);
			request.setAttribute("course", course);
			request.setAttribute("instructor", course.getInstructor().getFullName());
			request.setAttribute("instructors", instructorService.findAll());
			request.setAttribute("instructorId", course.getInstructor().getId());
		} catch (SQLException e) {
			e.printStackTrace();
			return "/all_courses.jsp";
		}
		return "/edit_course.jsp";
	
	}

}
