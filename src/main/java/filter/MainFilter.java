package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class MainFilter implements Filter {
	
	private FilterConfig filterConfig;
	
	@Override
    public void destroy() 
    {
        filterConfig = null;
    }
	
	@Override
    public void init(FilterConfig fConfig) throws ServletException
    {
        filterConfig = fConfig;
    }
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		if (((HttpServletRequest)request).getSession().getAttribute("currentEntity") == null &&
        		!((HttpServletRequest) request).getRequestURI().equals("/login") &&
        		!((HttpServletRequest) request).getRequestURI().equals("/registration")) {
            request.getRequestDispatcher("/login.jsp").forward(request, response); 
            return;
    	} else {
    		((HttpServletRequest)request).getRequestDispatcher(((HttpServletRequest) request).getRequestURI()).forward(request, response);
    	}
	}
	
}
