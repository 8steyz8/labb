package mk.ukim.finki.wp.lab.model.exceptions;

public class NoSuchElementException extends RuntimeException{

    public NoSuchElementException(){
        super("No such element!");
    }
}