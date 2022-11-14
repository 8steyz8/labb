package mk.ukim.finki.wp.lab.model;

import lombok.Data;

@Data //for getters&setters
public class Student {

    private String username;
    private String password;
    private String name;
    private String surname;

    public Student(String username, String password, String name, String surname) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.surname = surname;
    }
}
