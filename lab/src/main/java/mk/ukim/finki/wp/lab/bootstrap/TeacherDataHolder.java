package mk.ukim.finki.wp.lab.bootstrap;


import mk.ukim.finki.wp.lab.model.Teacher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

@Component
public class TeacherDataHolder {

    public static List<Teacher> teacherrs = new ArrayList<>();

    @PostConstruct
    public void init(){

//        teachers.add(new Teacher(123456L,"Dimitar","Trajanov"));
//        teachers.add(new Teacher(232323L,"Saso","Gramatikov"));
//        teachers.add(new Teacher(484848L,"Igor","Mishkovski"));
//        teachers.add(new Teacher(808080L,"Miroslav","Mirchev"));
//        teachers.add(new Teacher(202020L,"Hristina", "Mihajlovska"));

    }
}
