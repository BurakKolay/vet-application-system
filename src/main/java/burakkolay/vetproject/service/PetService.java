package burakkolay.vetproject.service;


import burakkolay.vetproject.exception.PetNotFoundWithIdException;
import burakkolay.vetproject.exception.PetNotFoundWithNameException;
import burakkolay.vetproject.model.DTO.PetDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.model.entity.Pet;
import burakkolay.vetproject.repository.PetRepository;
import burakkolay.vetproject.model.mapper.PetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Optional;


@Service
public class PetService {

    private final PetRepository petRepository;
    private final OwnerService ownerService;

    @Autowired
    public PetService(PetRepository petRepository, OwnerService ownerService) {
        this.petRepository = petRepository;
        this.ownerService = ownerService;
    }



    public List<Pet> getAllPets(){
        List<Pet> allPets = petRepository.findAll();
        return allPets;
    }

    public Pet getByName(String name){
        Optional<Pet> pet = petRepository.findByName(name);

        return pet.orElseThrow(()->{
            return new PetNotFoundWithNameException(name,"name");
        });
    }

    public Pet updatePet(Long id, PetDTO petDTO){
        Optional<Pet> byId= petRepository.findById(id);
        if(byId.isEmpty()){
            return null;
        }
        Pet updatedPet = byId.get();

        if(!StringUtils.isEmpty(petDTO.getAge())){
            updatedPet.setAge(petDTO.getAge());
        }
        if(!StringUtils.isEmpty(petDTO.getDescription())){
            updatedPet.setDescription(petDTO.getDescription());
        }
        if(!StringUtils.isEmpty(petDTO.getName())){
            updatedPet.setName(petDTO.getName());
        }

        return petRepository.save(updatedPet);
    }

    public Pet getById(Long id){
        Optional<Pet> byId= petRepository.findById(id);
        return byId.orElseThrow(()->{
            return new PetNotFoundWithIdException(id,"id");
        });
    }

    public Pet create(Long id,PetDTO petDTO){
        Pet pet = PetMapper.toEntity(petDTO);
        Owner owner=ownerService.getById(id);
        owner.getPet().add(pet);
        pet.setOwner(owner);
        return petRepository.save(pet);
    }

    public void delete(Long id){
        petRepository.deleteById(id);
    }


}
