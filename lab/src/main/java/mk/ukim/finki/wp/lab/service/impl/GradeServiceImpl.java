package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.exceptions.NoSuchElementException;
import mk.ukim.finki.wp.lab.repository.jpa.CourseRepository;
import mk.ukim.finki.wp.lab.repository.jpa.GradeRepository;
import mk.ukim.finki.wp.lab.repository.jpa.StudentRepository;
import mk.ukim.finki.wp.lab.service.GradeService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class GradeServiceImpl implements GradeService {
    private final GradeRepository gradeRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;

    public GradeServiceImpl(GradeRepository repository, CourseRepository courseRepository, StudentRepository studentRepository) {
        this.gradeRepository = repository;
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
    }

    @Override
    public Map<String, Grade> getGrades(List<Student> students, Long courseId) {
        Map<String, Grade> map = new HashMap<>();
        Course course = courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);

        students.forEach(s->{
            Grade grade = gradeRepository.findByStudentAndCourse(s, course);
            if (grade != null)
                map.put(s.getUsername(), grade);
        });

        return map;
    }


    @Override
    public Grade save(LocalDateTime timestamp, String grade, Course course, String studentId) {
        Student student = studentRepository.findById(studentId).orElseThrow(NoSuchElementException::new);
        if (grade.length()>1){
            throw new NoSuchElementException();
        }
        Character c = grade.charAt(0);
        Grade gradeObj = new Grade(timestamp, c, course, student);
        return gradeRepository.save(gradeObj);
    }

    @Override
    public Optional<Grade> getGrade(Long id) {
        return gradeRepository.findById(id);
    }

    @Override
    public Grade findByUsernameAndCourse(String username, Long id) {
        Student student = studentRepository.findById(username).orElseThrow(NoSuchElementException::new);
        Course course= courseRepository.findById(id).orElseThrow(NoSuchElementException::new);
        return gradeRepository.findByStudentAndCourse(student,course);
    }


}