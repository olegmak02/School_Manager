package controller.command;

import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import domain.Student;
import service.StudentServiceImpl;

public class Block implements Command {

	private StudentServiceImpl studentService = new StudentServiceImpl();
	
	@Override
	public String execute(HttpServletRequest request) {
		
		if (request.getParameter("block_student") != null) {
			Long studentId = Long.parseLong(request.getParameter("block_student"));
			try {
				studentService.setBlock(studentId);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		try {
			List<Student> students = studentService.getUnblockedStudents();
			request.setAttribute("students", students);
			return "/block.jsp";
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return "/admin.jsp";
	}

}
