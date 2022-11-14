package mk.ukim.finki.wp.lab.repository;

import mk.ukim.finki.wp.lab.bootstrap.StudentDataHolder;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StudentRepository {

    public List<Student> findAllStudents() {
        return StudentDataHolder.students;
    }


    public List<Student> findAllByNameOrSurname(String text) {
        return StudentDataHolder.students.stream().filter(s -> s.getName().contains(text) || s.getSurname().contains(text)).collect(Collectors.toList());

    }

}
