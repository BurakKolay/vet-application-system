package burakkolay.vetproject.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OwnerDTO {

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mail;

}
