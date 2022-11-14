package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Course;
import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class CourseDataHolder {

    public static List<Course> courses= new ArrayList<>();

    @PostConstruct
    public void init(){

        courses.add(new Course("Marketing","Marketing opis",new ArrayList<Student>()));
        courses.add(new Course("Ved dizajn","Veb dizajn opis",new ArrayList<Student>()));
        courses.add(new Course("Internet tehnologii","Internet tehnologii opis",new ArrayList<Student>()));
        courses.add(new Course("E-vlada","E-vlada opis",new ArrayList<Student>()));
        courses.add(new Course("Mrezna bezbednost","Mrezna bezbednost opis",new ArrayList<Student>()));
    }
}
