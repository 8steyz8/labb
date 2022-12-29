package mk.ukim.finki.wp.lab.model.exceptions;

public class NoDescException extends RuntimeException{
    public NoDescException ()
    {
        super("No description.!");
    }
}

