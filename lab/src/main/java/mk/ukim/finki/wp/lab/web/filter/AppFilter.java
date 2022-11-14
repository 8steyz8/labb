package mk.ukim.finki.wp.lab.web.filter;

import mk.ukim.finki.wp.lab.model.Course;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AppFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        Course course = (Course)request.getSession().getAttribute("chosenCourse");

        String path = request.getServletPath();

        if (!"/AddStudent".equals(path) &&!"/courses".equals(path) && !"/main.css".equals(path) && course==null) {
            response.sendRedirect("/courses?error=CourseNotSelected");
        } else {
            filterChain.doFilter(servletRequest,servletResponse);
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}
