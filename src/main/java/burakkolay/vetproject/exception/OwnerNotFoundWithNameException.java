package burakkolay.vetproject.exception;

public class OwnerNotFoundWithNameException extends RuntimeException{
    public OwnerNotFoundWithNameException(String firstName, String cause){
        super("Related "+firstName+"not found with : ["+cause+"]");
    }
}
