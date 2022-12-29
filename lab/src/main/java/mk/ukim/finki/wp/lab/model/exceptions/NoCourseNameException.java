package mk.ukim.finki.wp.lab.model.exceptions;

public class NoCourseNameException extends RuntimeException{
    public NoCourseNameException(){
        super("No course name.");
    }
}
