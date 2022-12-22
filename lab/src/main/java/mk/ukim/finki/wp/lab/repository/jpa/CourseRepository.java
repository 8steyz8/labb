package mk.ukim.finki.wp.lab.repository.jpa;


import mk.ukim.finki.wp.lab.model.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

//extends JpaRepository<Entity,ID>
@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {

}
