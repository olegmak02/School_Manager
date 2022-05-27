package controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Student;
import service.StudentServiceImpl;

public class Unblock implements Command {

	private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getParameter("unblock_student") != null) {
			Long studentId = Long.parseLong(request.getParameter("unblock_student"));
			try {
				studentService.setUnblock(studentId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			List<Student> students = studentService.getBlockedStudents();
			request.setAttribute("students", students);
			return "/unblock.jsp";
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return "/admin.jsp";
	}

}
