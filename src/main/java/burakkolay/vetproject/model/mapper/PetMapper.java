package burakkolay.vetproject.model.mapper;

import burakkolay.vetproject.model.DTO.PetDTO;
import burakkolay.vetproject.model.entity.Pet;


public class PetMapper {

    public static PetDTO toDTO(Pet pet){
        PetDTO petDTO = new PetDTO();
        petDTO.setType(pet.getType());
        petDTO.setAge(pet.getAge());
        petDTO.setDescription(pet.getDescription());
        petDTO.setGenus(pet.getGenus());
        petDTO.setName(pet.getName());

        return petDTO;
    }

    public static Pet toEntity(PetDTO petDTO){
        Pet pet = new Pet();

        pet.setAge(petDTO.getAge());
        pet.setDescription(petDTO.getDescription());
        pet.setGenus(petDTO.getGenus());
        pet.setName(petDTO.getName());
        pet.setType(petDTO.getType());

        return pet;
    }

}
