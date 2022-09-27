package burakkolay.vetproject.exception;

import lombok.Data;
import org.springframework.data.crossstore.ChangeSetPersister;

@Data
public class  UserNotFoundException extends RuntimeException {
    private String details;
    public UserNotFoundException(String entityName, String cause) {
        super("Related " + entityName + " not found with : [" + cause + "]");
    }
}