package mk.ukim.finki.wp.lab.service.impl;

        import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;
        import mk.ukim.finki.wp.lab.model.Course;
        import mk.ukim.finki.wp.lab.model.Student;
        import mk.ukim.finki.wp.lab.model.Teacher;
        import mk.ukim.finki.wp.lab.model.exceptions.InvalidArgumentsException;
        import mk.ukim.finki.wp.lab.model.exceptions.NoSuchTeacherException;
        import mk.ukim.finki.wp.lab.repository.CourseRepository;
        import mk.ukim.finki.wp.lab.service.CourseService;
        import mk.ukim.finki.wp.lab.service.StudentService;
        import mk.ukim.finki.wp.lab.service.TeacherService;
        import org.springframework.stereotype.Service;

        import java.util.List;
        import java.util.stream.Collectors;

 //biznis logikata
@Service
public class CourseServiceImpl implements CourseService {

    //injection
    private CourseRepository courseRepository;
    private StudentService studentService;

    private TeacherService teacherService;

    public CourseServiceImpl(CourseRepository courseRepository, StudentService studentService) {
        this.courseRepository = courseRepository;
        this.studentService = studentService;
    }

    @Override
    public List<Student> listStudentsByCourse(Long courseId) {

        return courseRepository.findAllStudentsByCourse(courseId);
    }

    @Override
    public Course addStudentInCourse(String username, Long courseId) {

        Student studentX=(Student) studentService.listAll().stream().filter(y->y.getUsername().equals(username)).collect(Collectors.toList()).get(0);
        Course courseX=courseRepository.findById(courseId);
        return courseRepository.addStudentToCourse(studentX,courseX);
    }

    @Override
    public List<Course> listAll() {
        return courseRepository.findAllCourses();
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseRepository.findById(Long.valueOf(courseId));
    }

     @Override
     public Course saveCourse(String name, String description, long teacherId) {
        if(name.isEmpty() || description.isEmpty() )
        {
            throw new InvalidArgumentsException();
        }
        Teacher teacher = teacherService.findById(teacherId).orElseThrow(NoSuchTeacherException::new);

        return courseRepository.save(name,description,teacher);
     }

     @Override
     public void deleteCourseById(Long courseId) {
         CourseDataHolder.courses.removeIf(c->c.getCourseId().equals(courseId));
     }


 }
