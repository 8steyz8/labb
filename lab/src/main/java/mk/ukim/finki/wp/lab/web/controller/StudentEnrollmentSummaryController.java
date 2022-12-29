package mk.ukim.finki.wp.lab.web.controller;

import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("StudentEnrollmentSummary")
public class StudentEnrollmentSummaryController {
    public final SpringTemplateEngine springTemplateEngine;

    public final StudentService studentService;

    public final CourseService courseService;

    public final GradeService gradeService;

    public StudentEnrollmentSummaryController(SpringTemplateEngine springTemplateEngine, StudentService studentService, CourseService courseService, GradeService gradeService) {
        this.springTemplateEngine = springTemplateEngine;
        this.studentService = studentService;
        this.courseService = courseService;
        this.gradeService = gradeService;
    }

    @GetMapping
    public String getStudentEnrollmentSummaryPage(HttpServletRequest req, Model model)
    {

        HashMap<String, Grade> map = new HashMap<>();

        long chosenCourseId = (long) req.getSession().getAttribute("chosenCourse");

        List<Student> students = courseService.listStudentsByCourse(chosenCourseId);
        students.forEach(s->{
            map.put(s.getUsername(), gradeService.findByUsernameAndCourse(s.getUsername(), chosenCourseId));
        });

        model.addAttribute("courseName",courseService.getCourse(chosenCourseId));
        model.addAttribute("students",courseService.listStudentsByCourse(chosenCourseId));
        model.addAttribute("bodyContent","studentsInCourse");
        model.addAttribute("grades",map);

        return "master-template";
    }

    @PostMapping
    public String postStudentEnrollmentSummaryPage(HttpServletRequest req, HttpServletResponse resp,Model model) throws IOException {
        HashMap<String, Grade> map = new HashMap<>();


        long chosenCourseId = (long) req.getSession().getAttribute("chosenCourse");

        String username = req.getParameter("student");

        //dodadi go selektiraniot student vo lsitata na kursot
        courseService.addStudentInCourse(username,chosenCourseId);
        model.addAttribute("courseName",courseService.getCourse(chosenCourseId));
        model.addAttribute("students",courseService.listStudentsByCourse(chosenCourseId));
        List<Student> students = courseService.listStudentsByCourse(chosenCourseId);

        //List<Grade> listGrades=students.stream().map(s->gradeService.findByUsernameAndCourse(s.getUsername(),chosenCourseId)).collect(Collectors.toList());
//        students.forEach(s->{
//            map.put(s.getUsername(), gradeService.findByUsernameAndCourse(s.getUsername(), chosenCourseId));
//        });
//        model.addAttribute("grades",map);
        model.addAttribute("bodyContent","studentsInCourse");


        return "master-template";
    }
}
