package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    List<Student> listStudentsByCourse(Long courseId);

    Course addStudentInCourse(String username, Long courseId);

    List<Course> listAll();

    Optional<Course> getCourse(Long courseId);

    Course saveCourse(String name, String description, long teacherId);

    void deleteById(Long courseId);

    Course saveCourse2(Course course, long id);
}
