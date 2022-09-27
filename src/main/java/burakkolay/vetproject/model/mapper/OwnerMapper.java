package burakkolay.vetproject.model.mapper;

import burakkolay.vetproject.model.DTO.OwnerDTO;
import burakkolay.vetproject.model.entity.Owner;

public class OwnerMapper {

    public static OwnerDTO toDTO(Owner owner){
        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setMail(owner.getMail());
        ownerDTO.setFirstName(owner.getFirstName());
        ownerDTO.setLastName(owner.getLastName());
        ownerDTO.setPhoneNumber(owner.getPhoneNumber());

        return ownerDTO;
    }

    public static Owner toEntity(OwnerDTO ownerDTO){
        Owner owner = new Owner();
        owner.setMail(ownerDTO.getMail());
        owner.setFirstName(ownerDTO.getFirstName());
        owner.setLastName(ownerDTO.getLastName());
        owner.setPhoneNumber(ownerDTO.getPhoneNumber());

        return owner;
    }
}
