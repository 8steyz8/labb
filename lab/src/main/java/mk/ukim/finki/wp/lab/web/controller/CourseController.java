package mk.ukim.finki.wp.lab.web.controller;


import mk.ukim.finki.wp.lab.service.CourseService;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/courses")
public class CourseController {

    public final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public String getCoursesPage(@RequestParam(required = false) String error, Model model)
    {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }

        model.addAttribute("courses",courseService.listAll());
        return "listCourses";
    }

    @PostMapping("/add")
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

    @DeleteMapping("/delete/{id}") // vaka e so path variable
    public String deleteCourseById(@PathVariable Long id) {
        courseService.deleteCourseById(id);
        return "redirect:/courses";
    }
}
