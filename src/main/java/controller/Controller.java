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

import controller.command.Command;
import controller.command.Login;
import controller.command.Registration;
import controller.command.student.StudentPage;

@WebServlet(urlPatterns = "/")
public class Controller extends HttpServlet {
	private Map<String, Command> commands = new HashMap<>();

    @Override
    public void init(ServletConfig servletConfig){
    	commands.put("/", (req) -> "/login.jsp");
    	commands.put("/registration", new Registration());
		commands.put("/login", new Login());
		commands.put("/student", new StudentPage());
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
