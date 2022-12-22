package mk.ukim.finki.wp.lab.repository.impl;

import mk.ukim.finki.wp.lab.bootstrap.CourseDataHolder;
import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

//funkcii so koi se barata so data
@Repository
public class CourseRepositoryImpl {


    public List<Course> findAllCourses() {
        return CourseDataHolder.courses;
    }


    public Course findById(Long courseId) throws NoSuchElementException//optional zosto moze ili da go ima ili da go nema
    {
        Optional<Course> find = CourseDataHolder.courses.stream().filter(c -> c.getCourseId().equals(courseId)).findFirst();
        if (find.isEmpty()) {
            throw new NoSuchElementException();

        }
        return find.get();

    }

    public Course save(String name, String description, Teacher teacher)
    {
        //ako postoi kurs so toa ime i toj profesor izbrisi go
        CourseDataHolder.courses.removeIf(c->c.getName().equals(name));

        Course c= new Course(name,description,teacher);
        CourseDataHolder.courses.add(c);
        return c;

    }

    public List<Student> findAllStudentsByCourse(Long courseId) {
        return findById(courseId).getStudents();
    }

    public Course addStudentToCourse(Student student, Course course)
    {
           if(!CourseDataHolder.courses.contains(course))
           {
               throw new NoSuchElementException();
           }
           course.getStudents().removeIf(s->s.getUsername().equals(student.getUsername())); //izbrishi ako postoi
           course.getStudents().add(student);
           return course;
    }


}
