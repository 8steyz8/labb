package mk.ukim.finki.wp.lab.model.exceptions;

public class NoSuchTeacherException extends RuntimeException{

    public NoSuchTeacherException()
    {
        super("No such teacher!");
    }

}
