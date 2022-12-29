package mk.ukim.finki.wp.lab.service.impl;

        import mk.ukim.finki.wp.lab.model.Course;
        import mk.ukim.finki.wp.lab.model.Student;
        import mk.ukim.finki.wp.lab.model.Teacher;
        import mk.ukim.finki.wp.lab.model.exceptions.*;
        import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
        import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
        import mk.ukim.finki.wp.lab.repository.jpa.TeacherRepository;
        import mk.ukim.finki.wp.lab.service.CourseService;
        import org.springframework.stereotype.Service;

        import javax.transaction.Transactional;
        import java.util.List;
        import java.util.Optional;

//biznis logikata
@Service
public class CourseServiceImpl implements CourseService {

    //injection
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    private final TeacherRepository teacherRepository;

    public CourseServiceImpl(CourseRepository courseRepository, TeacherRepository teacherRepository, StudentRepository studentRepository) {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository=teacherRepository;
    }

     @Override
     public List<Student> listStudentsByCourse(Long courseId) {
         try {
             return this.findAllStudentsByCourse(courseId);
         }catch (NoSuchElementException exception){
             System.out.println("Course ID "+ courseId+" doesn't exist.");
         }
         return null;
     }

     public List<Student> findAllStudentsByCourse(Long courseId){
         Course course = courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);
         return course.getStudents();
     }

     @Override
     @Transactional
     public Course addStudentInCourse(String username, Long courseId) {
         Student student = studentRepository.findById(username)
                 .orElseThrow(NoSuchElementException::new);
         Optional<Course> byId = courseRepository.findById(courseId);
         if (byId.isPresent()) {
             Course course = byId.get();
             course.addStudent(student);
             courseRepository.save(course);
         }
         return null;
     }
    @Override
    public List<Course> listAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> getCourse(Long courseId) {
        return courseRepository.findById(courseId);
    }


     @Override
     public Course saveCourse(String name, String description, long teacherId) {
        if(name.isEmpty() || description.isEmpty() )
        {
            throw new InvalidArgumentsException();
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(NoSuchTeacherException::new);

        Course course= new Course(name,description,teacher);

        return courseRepository.save(course);
     }


     @Override
     public void deleteById(Long courseId) {
         courseRepository.deleteById(courseId);
     }

    @Override
    public Course saveCourse2(Course course, long id) {
        if(course.getName().isEmpty())
            throw new NoCourseNameException();
        if(course.getDescription().isEmpty())
            throw new NoDescException();
        Teacher teacher = teacherRepository.findById(id).orElseThrow(NoSuchTeacherException::new);
        course.setTeacher(teacher);
        return courseRepository.save(course);
    }


}
