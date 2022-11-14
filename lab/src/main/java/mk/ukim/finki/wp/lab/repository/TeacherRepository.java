package mk.ukim.finki.wp.lab.repository;


import mk.ukim.finki.wp.lab.bootstrap.TeacherDataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepository {

    public List<Teacher> findAll(){
        return TeacherDataHolder.teachers;
    };

    public Optional<Teacher> findById(Long id){
        return TeacherDataHolder.teachers.stream().filter(t->t.getId().equals(id)).findFirst();
    }

}
