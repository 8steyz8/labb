package mk.ukim.finki.wp.lab.bootstrap;

import mk.ukim.finki.wp.lab.model.Student;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class StudentDataHolder {

    public static List<Student> students = new ArrayList<>();

    @PostConstruct
    public void init(){
        students.add(new Student("203060","123ok","Anastazija","Kovachevikj"));
        students.add(new Student("205060","*0123*","Ana","Ivanova"));
        students.add(new Student("202290","_lol_","Nikola","Petrushev"));
        students.add(new Student("203210","0pop0","Eva","Manova"));
        students.add(new Student("203179","_wow_","Matea","Petrevski"));
    }
}
