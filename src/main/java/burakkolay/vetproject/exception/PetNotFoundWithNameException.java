package burakkolay.vetproject.exception;

public class PetNotFoundWithNameException extends RuntimeException {

    public PetNotFoundWithNameException(String petName, String cause){
        super("Related "+petName+"not found with : ["+cause+"]");
    }
}
