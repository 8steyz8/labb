package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Teacher;
import mk.ukim.finki.wp.lab.service.CourseService;
import mk.ukim.finki.wp.lab.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/courses")
public class CourseController {

    public final CourseService courseService;
    private final TeacherService teacherService;

    public CourseController(CourseService courseService, TeacherService teacherService) {
        this.courseService = courseService;
        this.teacherService = teacherService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model) {

        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Course> list = courseService.listAll().stream().sorted(Comparator.comparing(Course::getName)).collect(Collectors.toList());
        model.addAttribute("courses", list);
        model.addAttribute("bodyContent","listCourses");
        return "master-template";
    }

    @GetMapping("/add-course")
    public String getAddCoursePage(Model model) {
        List<Teacher> teachers = this.teacherService.findAll();
        model.addAttribute("teachers", teachers);
        model.addAttribute("bodyContent","add-course");
        return "master-template";
    }


    @GetMapping("/reversed")
    public String shuffle(Model model) {
        List<Course> list = courseService.listAll().stream().sorted(Comparator.comparing(Course::getName).reversed()).collect(Collectors.toList());
        model.addAttribute("courses", list);
        model.addAttribute("bodyContent","reversed");
        return "master-template";
    }

    @PostMapping("/added-course")
    public String saveCourse(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam Long id){
        try {
            courseService.saveCourse(name, description, id);
        } catch (RuntimeException exception) {
            return "redirect:/courses?error=" + exception.getMessage();
        }

        return "redirect:/courses";
    }

    @GetMapping("/delete/{id}") // vaka e so path variable
    public String deleteCourseById(@PathVariable Long id) {
        courseService.deleteById(id);
        return "redirect:/courses";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditCoursePage(@PathVariable Long id, Model model) {

        Course c = courseService.getCourse(id).get();

        if (this.courseService.listAll().contains(c)) { // ako vo listata go ima id-to

            courseService.deleteById(id);

            //Course course = this.courseService.findCourseById(id).get();
            List<Teacher> teachers = this.teacherService.findAll();

            model.addAttribute("course", c);
            model.addAttribute("teachers", teachers);

            model.addAttribute("bodyContent","add-course");
            return "master-template";
        }
        return "redirect:/courses?error=CourseNotFound";
    }



}
