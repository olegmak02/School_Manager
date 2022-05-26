package controller.command.instructor;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import domain.Assignment;
import domain.Course;
import domain.Result;
import domain.Student;
import service.logic.AssignmentServiceImpl;
import service.logic.CourseServiceImpl;
import service.logic.ResultServiceImpl;

public class CoursePage implements Command {

	private AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
	private ResultServiceImpl resultService = new ResultServiceImpl();
	private CourseServiceImpl courseService = new CourseServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		String but_choose = (String) request.getParameter("but_choose");
		Long courseId = (request.getParameter("choose") == null) ? Long.parseLong(but_choose.split(":")[1]) : Long.parseLong(request.getParameter("choose"));
		if (but_choose != null) {
			Long studentId = Long.parseLong(but_choose.split(":")[0]);
			try {
				int assess = Integer.parseInt((String) request.getParameter("assess"));
				if (assess >= 60 && assess <= 100) {
					Assignment assignment = assignmentService.findByStudentAndCourse(studentId, courseId);
					Result result = resultService.findByAssignmentId(assignment.getId());
					if (result == null) {
						result = new Result();
						result.setAssignment(assignment);
					}
					result.setGrade(assess);
					resultService.save(result);
				}
			} catch (NumberFormatException | SQLException e) {
				e.printStackTrace();
			}
		}
		Course course;
		try {
			course = courseService.findById(courseId);
			List<Student> students;
			List<Result> results = new ArrayList<>();
			students = assignmentService.findStudentsByCourse(courseId);
			for(int i = 0; i < students.size(); i++) {
				Assignment assignment;
				assignment = assignmentService.findByStudentAndCourse(students.get(i).getId(), courseId);
				results.add(resultService.findByAssignmentId(assignment.getId()));
			}
			
			if (course.getBeginDate().isBefore(LocalDate.now()))
					request.setAttribute("results", results);
			request.setAttribute("students", students);
			request.setAttribute("choose", courseId.toString());
		} catch (SQLException e) {
			e.printStackTrace();
			return "/instructor.jsp";
		}
		return "/course_result.jsp";
	}

}
