package embrace.Exceptions;

public class NothingWasFoundException extends Exception{
    public NothingWasFoundException() {
        super("No object found based on the given properties.");
    }
}
