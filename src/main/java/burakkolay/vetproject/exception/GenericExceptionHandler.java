package burakkolay.vetproject.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GenericExceptionHandler {

    @ExceptionHandler(PetNotFoundWithNameException.class)
    public ResponseEntity<Map<String,String>> handleApplicantNotFoundException(PetNotFoundWithNameException e){
        Map<String,String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message",e.getMessage());
        errorResponseMap.put("error_cause",e.getCause().toString());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(PetNotFoundWithIdException.class)
    public ResponseEntity<Map<String,String>> handleApplicantNotFoundException(PetNotFoundWithIdException e){
        Map<String,String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message",e.getMessage());
        errorResponseMap.put("error_cause",e.getCause().toString());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(OwnerNotFoundWithNameException.class)
    public ResponseEntity<Map<String,String>> handleApplicantNotFoundException(OwnerNotFoundWithNameException e){
        Map<String,String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message",e.getMessage());
        errorResponseMap.put("error_cause",e.getCause().toString());

        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(OwnerNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(OwnerNotFoundException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_cause",exception.getCause().toString());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponseMap);
    }

    @ExceptionHandler(CustomJwtException.class)
    public ResponseEntity<Map<String, String>> handleCustomJwtException(CustomJwtException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        errorResponseMap.put("error_status", exception.getHttpStatus().toString());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponseMap);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<Map<String, String>> handleAccessDeniedException(AccessDeniedException exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorResponseMap);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, String>> handleEntityNotFoundException(Exception exception) {
        Map<String, String> errorResponseMap = new HashMap<>();
        errorResponseMap.put("error_message", exception.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponseMap);
    }
}
