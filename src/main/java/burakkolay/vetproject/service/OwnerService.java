package burakkolay.vetproject.service;

import burakkolay.vetproject.exception.OwnerNotFoundException;
import burakkolay.vetproject.exception.OwnerNotFoundWithNameException;
import burakkolay.vetproject.model.DTO.OwnerDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.model.mapper.OwnerMapper;
import burakkolay.vetproject.repository.OwnerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;

@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public OwnerService(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    public List<Owner> getAllOwners(){
        List<Owner> allOwners = ownerRepository.findAll();
        return allOwners;
    }

    public Owner getOwnerByName(String firstName){
        Optional<Owner> owner = ownerRepository.findByFirstName(firstName);

        return owner.orElseThrow(()->{
            return new OwnerNotFoundWithNameException(firstName,"first name");
        });
    }

    public Owner updateOwner(Long id, OwnerDTO ownerDTO){
        Optional<Owner> byId= ownerRepository.findById(id);
        if(!byId.isPresent()){
            return null;
        }
        Owner updatedOwner = byId.get();
        if(!StringUtils.isEmpty(ownerDTO.getPhoneNumber())){
            updatedOwner.setPhoneNumber(ownerDTO.getPhoneNumber());
        }
        if(!StringUtils.isEmpty(ownerDTO.getMail())){
            updatedOwner.setMail(ownerDTO.getMail());
        }
        if(!StringUtils.isEmpty(ownerDTO.getFirstName())){
            updatedOwner.setFirstName(ownerDTO.getFirstName());
        }
        if(!StringUtils.isEmpty(ownerDTO.getLastName())){
            updatedOwner.setLastName(ownerDTO.getLastName());
        }

        return ownerRepository.save(updatedOwner);
    }


    public Owner getById(Long id){
        Optional<Owner> byId=ownerRepository.findById(id);
        return byId.orElseThrow(()->{
            return new OwnerNotFoundException(id,"id");
        });
    }

    public Owner create(OwnerDTO ownerDTO){
        Owner owner = OwnerMapper.toEntity(ownerDTO);
        return ownerRepository.save(owner);
    }

    public void delete(Long id){
        ownerRepository.deleteById(id);
    }

}
