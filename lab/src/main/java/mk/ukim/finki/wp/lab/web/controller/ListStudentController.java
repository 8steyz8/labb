package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping( "/AddStudent")
public class ListStudentController {
    public final StudentService studentService;
    public final SpringTemplateEngine springTemplateEngine;

    public final CourseService courseService;

    public ListStudentController(StudentService studentService, SpringTemplateEngine springTemplateEngine, CourseService courseService) {
        this.studentService = studentService;
        this.springTemplateEngine = springTemplateEngine;
        this.courseService = courseService;
    }

    @GetMapping
    public String getAddStudentPage(HttpServletRequest req, Model model){
        String courseId = req.getParameter("courseId");
        req.getSession().setAttribute("chosenCourse",Long.valueOf(courseId) );

        Optional<Course> course = courseService.getCourse(Long.valueOf(courseId));

        req.getSession().setAttribute("course",course.get());

        if (req.getSession().getAttribute("chosenCourse") == null) {
            return "redirect:/listCourses";
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


        model.addAttribute("students",notEnrolled);
        //context.setVariable("students",studentService.listAll());

        // context.setVariable("enrolled",enrolledStudents);
        //context.setVariable("EnrolledStudents",courseService.listStudentsByCourse(Long.valueOf(courseId)));
        model.addAttribute("courseId", courseId);
        model.addAttribute("bodyContent","listStudents");
        return "master-template";
    }
}
