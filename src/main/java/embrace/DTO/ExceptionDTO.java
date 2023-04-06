package embrace.DTO;

public class ExceptionDTO {
    private Throwable exception;
    private String message;

    public ExceptionDTO(Throwable exception) {
        this.exception = exception;
        message = exception.getMessage();
    }

    public String getMessage() {
        return message;
    }
}
