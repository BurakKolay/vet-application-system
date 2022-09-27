package burakkolay.vetproject.exception;

public class PetNotFoundWithIdException extends RuntimeException{

    public PetNotFoundWithIdException(Long petName, String cause){
        super("Related "+petName+"not found with : ["+cause+"]");
    }
}
