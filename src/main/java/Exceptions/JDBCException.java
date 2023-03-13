package Exceptions;

public class JDBCException extends Exception{
    public JDBCException(Throwable cause) {
        super("An exception was thrown during working with JDBC repos.", cause);
    }
}
