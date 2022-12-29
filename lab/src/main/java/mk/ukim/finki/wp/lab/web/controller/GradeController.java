package mk.ukim.finki.wp.lab.web.controller;



import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.service.GradeService;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/add-grade")
public class GradeController {

    private final GradeService gradeService;
    private final StudentService studentService;


    public GradeController(GradeService gradeService, StudentService studentService) {
        this.gradeService = gradeService;
        this.studentService = studentService;
    }

    @GetMapping("/{studentId}")
    public String getAddGradePage(HttpServletRequest req,
                                  @PathVariable String studentId,
                                  Model model){

        req.getSession().setAttribute("student", studentId);
        /*        model.addAttribute("grade", grade);*/
        model.addAttribute("bodyContent","add-grade");
        return "master-template";
    }

    @PostMapping
    protected String saveGrade(@RequestParam String grade,
                               @RequestParam("timestamp") @DateTimeFormat(
                                       iso = DateTimeFormat.ISO.DATE_TIME)
                               LocalDateTime timestamp,
                               @RequestParam(required = false) Long id,
                               HttpServletRequest req){

            Course course = (Course) req.getSession().getAttribute("course");
            String studentUsername = (String) req.getSession().getAttribute("student");
            gradeService.save(timestamp, grade, course, studentUsername);


        return "redirect:/StudentEnrollmentSummary";
    }
}