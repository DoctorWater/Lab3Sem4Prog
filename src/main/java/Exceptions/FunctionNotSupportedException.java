package Exceptions;

public class FunctionNotSupportedException extends Exception{
    public FunctionNotSupportedException() {
        super("Function you try to call is not supported.");
    }
}
