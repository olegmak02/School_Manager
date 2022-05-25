package controller.command.student;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import controller.command.Command;
import domain.Result;

import domain.Assignment;
import domain.Course;
import domain.Student;
import service.AssignmentServiceImpl;
import service.ResultServiceImpl;


public class MyCourses implements Command {

	private AssignmentServiceImpl assignmentService = new AssignmentServiceImpl();
	private ResultServiceImpl resultService = new ResultServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		List<Assignment> assignments;
		
		try {
			assignments = getAllCoursesByStudent(request);
		} catch (SQLException e) {
			e.printStackTrace();
			return "/my_courses.jsp";
		}
		
		List<Course> courses = new ArrayList<>();
		if (request.getParameter("content") == null) {
			return "/my_courses.jsp";
		}

		int content = Integer.parseInt(request.getParameter("content"));
		if (content == 1) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getBeginDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		} else if (content == 2) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getBeginDate().isBefore(LocalDate.now()) && x.getCourse().getFinishDate().isAfter(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		} else if (content == 3) {
			assignments = assignments.stream().filter(x -> (x.getCourse().getFinishDate().isBefore(LocalDate.now()))).collect(Collectors.toList());
			courses = assignments.stream().map(x -> x.getCourse()).collect(Collectors.toList());
		}
		
		List<Result> results = new ArrayList<>();
		
		for (Assignment assignment: assignments) {
			try {
				results.add(resultService.findByAssignmentId(assignment.getId()));
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("results", results);
		request.setAttribute("courses", courses);
		
		return "/my_courses.jsp";
	}
	
	private List<Assignment> getAllCoursesByStudent(HttpServletRequest request) throws SQLException {
		try {
			Long id = ((Student)request.getSession().getAttribute("currentEntity")).getId();
			return assignmentService.findByStudent(id);
		} catch (SQLException e) {
			throw e;
		}
	}
}
