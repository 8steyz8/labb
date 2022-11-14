package mk.ukim.finki.wp.lab.model.exceptions;

public class NoSelectedCourseException extends RuntimeException{

    public NoSelectedCourseException ()
    {
        super("No course selected. Please select a course!");
    }
}
