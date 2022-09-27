package burakkolay.vetproject.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CustomJwtException extends RuntimeException{
    private final String message;
    private final HttpStatus httpStatus;

}

