package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    public final CourseService courseService;
    private TeacherService teacherService;

    public CourseController(CourseService courseService,TeacherService teacherService ) {
        this.courseService = courseService;
        this.teacherService=teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model)
    {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }


        List<Course> list = courseService.listAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
        model.addAttribute("courses",list);
        return "listCourses";
    }

    @GetMapping("/add-course")
    public String getAddCoursePage(Model model){
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        return "add-course";
    }

    @PostMapping("/added-course")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long id){
        try {
            courseService.saveCourse(name,description,id);
        }catch (RuntimeException exception)
        {
            return "redirect:/courses?error=" + exception.getMessage();
        }

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}") // vaka e so path variable
    public String deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model){
        if(this.courseService.getCourse(id)!=null){
            Course course = this.courseService.getCourse(id);
            List<Teacher> teachers = this.teacherService.findAll();
            model.addAttribute("course", course);
            model.addAttribute("teachers", teachers);
            return "add-course";
        }
        return "redirect:/courses?error=CourseNotFound";

    }
}
