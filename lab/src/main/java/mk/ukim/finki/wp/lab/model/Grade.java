package mk.ukim.finki.wp.lab.model;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import javax.persistence.Id;

@Entity
@Data
public class Grade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Character grade;
    @OneToOne
    private Student student;
    @OneToOne
    private Course course;
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime timestamp;

    public Grade(Long id, Character grade, Student student, Course course, LocalDateTime timestamp) {
        this.id = id;
        this.grade = grade;
        this.student = student;
        this.course = course;
        this.timestamp = timestamp;
    }

    public Grade(LocalDateTime timestamp, Character c, Course course, Student student) {

    }
}
