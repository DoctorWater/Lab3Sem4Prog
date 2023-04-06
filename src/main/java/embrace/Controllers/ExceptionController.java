package embrace.Controllers;

import embrace.DTO.ExceptionDTO;
import embrace.Exceptions.NothingWasFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(value = NothingWasFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDTO handleException(NothingWasFoundException e){
        return new ExceptionDTO(e);
    }

    @ExceptionHandler(value = IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ExceptionDTO handleException(IllegalArgumentException e){
        return new ExceptionDTO(e);
    }
}
