package mk.ukim.finki.wp.lab.service;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Grade;
import mk.ukim.finki.wp.lab.model.Student;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface GradeService {
    Map<String, Grade> getGrades(List<Student> students, Long courseId);

    Grade save(LocalDateTime timestamp, String grade, Course course, String studentId);

    void deleteById(Long id);

    Optional<Grade> getGrade(Long id);

    void save(Long id, String grade, LocalDateTime timestamp);
}
