/*
package mk.ukim.finki.wp.lab.repository.impl;


import mk.ukim.finki.wp.lab.bootstrap.TeacherDataHolder;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class TeacherRepositoryImpl {

    public List<Teacher> findAll(){
        return TeacherDataHolder.teacherrs;
    };

    public Optional<Teacher> findById(Long id){
        return TeacherDataHolder.teacherrs.stream().filter(t->t.getId().equals(id)).findFirst();
    }

}
*/
