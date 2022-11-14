package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/CreateStudent")
public class CreateStudentServlet extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;

    public final StudentService studentService;

    public CreateStudentServlet(SpringTemplateEngine springTemplateEngine, StudentService studentService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //check if a course is selected, if not redirect
        if (req.getSession().getAttribute("chosenCourse") == null) {
            resp.sendRedirect("/listCourses");
        }
        resp.sendRedirect("/AddStudent?courseId=" + req.getSession().getAttribute("chosenCourse"));
        //springTemplateEngine.process("listStudents.html", new WebContext(req, resp, req.getServletContext()), resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        studentService.save(username, password, name, surname);

        resp.sendRedirect("/AddStudent?courseId=" + req.getSession().getAttribute("chosenCourse"));

    }
}
