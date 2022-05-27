package controller.command;

import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;

import domain.Course;
import domain.Instructor;
import service.logic.CourseServiceImpl;
import service.logic.InstructorServiceImpl;
import util.Validation;

public class CreateCourse implements Command {
	private InstructorServiceImpl instructorService = new InstructorServiceImpl();
	private CourseServiceImpl courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if(request.getParameter("created") != null) {
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
					return "Не удалось создать курс из-за проблем с базой данных";
				}
				request.setAttribute("message", "Курс успешно создан");
			};
		}
		
		try {
			request.setAttribute("instructors", instructorService.findAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return "/create_course.jsp";
	}

}
