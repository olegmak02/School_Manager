package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controller.command.AdminPage;
import controller.command.Block;
import controller.command.Command;
import controller.command.Courses;
import controller.command.CoursesList;
import controller.command.CreateCourse;
import controller.command.DeleteCourse;
import controller.command.EditCourse;
import controller.command.Login;
import controller.command.Logout;
import controller.command.Registration;
import controller.command.SuccessInstructorRegistration;
import controller.command.Unblock;
import controller.command.instructor.CoursePage;
import controller.command.instructor.InstructorCourses;
import controller.command.instructor.InstructorPage;
import controller.command.student.CourseRegistrationPage;
import controller.command.student.MyCourses;
import controller.command.student.OtherCourses;
import controller.command.student.StudentPage;
import controller.command.student.SuccessRegistration;

@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {
	private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig){
    	commands.put("/", (req) -> "/login.jsp");
    	commands.put("/registration", new Registration());
	commands.put("/login", new Login());
	commands.put("/student", new StudentPage());
	commands.put("/my_courses", new MyCourses());
	commands.put("/courses", new Courses());
	commands.put("/course", new CoursePage());
	commands.put("/logout", new Logout());
	commands.put("/other_courses", new OtherCourses());
	commands.put("/course_registration", new CourseRegistrationPage());
	commands.put("/success_registration", new SuccessRegistration());
	commands.put("/instructor", new InstructorPage());
	commands.put("/result_course", new CoursePage());
	commands.put("/instructor_courses", new InstructorCourses());
	commands.put("/admin", new AdminPage());
	commands.put("/registration_instructor", (req) -> "/registration_instructor.jsp");
	commands.put("/success_instructor_registration", new SuccessInstructorRegistration());
	ommands.put("/block", new Block());
	commands.put("/unblock", new Unblock());
	commands.put("/create_course", new CreateCourse());
	commands.put("/all_courses", new CoursesList());
	commands.put("/edit_course", new EditCourse());
	commands.put("/delete_course", new DeleteCourse());
    }

    @Override
    protected void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        processRequest(request, response);
    }

    public void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String path = request.getRequestURI();
        Command command = commands.get(path);
        String page = command.execute(request);
		
        request.getRequestDispatcher(page).forward(request, response);
	}
}
