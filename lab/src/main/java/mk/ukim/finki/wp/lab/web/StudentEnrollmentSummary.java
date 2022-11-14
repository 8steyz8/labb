package mk.ukim.finki.wp.lab.web;

import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/StudentEnrollmentSummary")
public class StudentEnrollmentSummary extends HttpServlet {

    public final SpringTemplateEngine springTemplateEngine;

    public final StudentService studentService;

    public final CourseService courseService;

    public StudentEnrollmentSummary(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/listCourses");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        long chosenCourseId = (long) req.getSession().getAttribute("chosenCourse");

        //if there is no student selected we want to redirect to /AddStudent
        if (req.getParameter("student") == null){
            resp.sendRedirect("/AddStudent?courseId="+chosenCourseId);
            return;
        }
        //zemi go username
        String username = req.getParameter("student");

        //dodadi go selektiraniot student vo lsitata na kursot
        courseService.addStudentInCourse(username,chosenCourseId);

        WebContext context=new WebContext(req,resp,req.getServletContext());
        context.setVariable("courseName",courseService.getCourse(chosenCourseId));
        context.setVariable("students",courseService.listStudentsByCourse(chosenCourseId));

        this.springTemplateEngine.process("studentsInCourse.html", context, resp.getWriter());
    }
}
