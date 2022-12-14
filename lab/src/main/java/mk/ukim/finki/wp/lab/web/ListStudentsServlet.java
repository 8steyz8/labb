package mk.ukim.finki.wp.lab.web;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebServlet(urlPatterns = "/AddStudentsd")
public class ListStudentsServlet extends HttpServlet {

    public final StudentService studentService;
    public final SpringTemplateEngine springTemplateEngine;

    public final CourseService courseService;

    public ListStudentsServlet(StudentService studentService, SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String courseId = req.getParameter("courseId");
        req.getSession().setAttribute("chosenCourse",Long.valueOf(courseId) );

        Optional<Course> course = courseService.getCourse(Long.valueOf(courseId));

        req.getSession().setAttribute("course",course.get());

        if (req.getSession().getAttribute("chosenCourse") == null) {
            resp.sendRedirect("/listCourses");
        }

//        //studenti sto go slusaat
        List<Student> enrolledStudents= courseService.listStudentsByCourse(Long.valueOf(courseId));
//
        List<Student> allStudents=studentService.listAll();

        List<Student> notEnrolled=new ArrayList<>();


           for (Student s:
                   allStudents) {
               boolean flag=true;
               for (Student a:
                       enrolledStudents) {
                   if((s.getUsername().equals(a.getUsername())))
                   {
                       flag=false;
                   }
               }
               if(flag)
               {
                   notEnrolled.add(s);
               }

           }


        WebContext context=new WebContext(req,resp, req.getServletContext());
        context.setVariable("students",notEnrolled);
        //context.setVariable("students",studentService.listAll());

       // context.setVariable("enrolled",enrolledStudents);
        //context.setVariable("EnrolledStudents",courseService.listStudentsByCourse(Long.valueOf(courseId)));
        context.setVariable("courseId", courseId);
        context.setVariable("bodyContent","listStudents");
        springTemplateEngine.process("master-template.html",context,resp.getWriter());

    }

}
