package burakkolay.vetproject.model.DTO;

import burakkolay.vetproject.model.entity.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PetDTO {

    private String genus;
    private Type type;
    private String name;
    private int age;
    private String description;

}
