package mk.ukim.finki.wp.lab.service.impl;

import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.repository.StudentRepository;
import mk.ukim.finki.wp.lab.service.StudentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {

  private  StudentRepository studentRepository;

    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }


    @Override
    public List<Student> listAll() {

        return studentRepository.findAllStudents();
    }

    @Override
    public List<Student> searchByNameOrSurname(String text) {

        return studentRepository.findAllByNameOrSurname(text);
    }

    @Override
    public Student save(String username, String password, String name, String surname) throws IllegalArgumentException {

        Student s= new Student(username,password,name,surname);
        if(s.getName().isEmpty() || s.getUsername().isEmpty() || s.getPassword().isEmpty() || s.getSurname().isEmpty())
        {
            throw new IllegalArgumentException();
        }
        studentRepository.findAllStudents().removeIf(r->r.getUsername().equals(username));
        studentRepository.findAllStudents().add(s);
        return s;
    }
}
