package burakkolay.vetproject.exception;

public class OwnerNotFoundException extends RuntimeException{
    public OwnerNotFoundException(Long id,String cause){
        super("Related "+id+"not found with : ["+cause+"]");
    }

}
