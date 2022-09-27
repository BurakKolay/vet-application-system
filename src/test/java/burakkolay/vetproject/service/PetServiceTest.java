package burakkolay.vetproject.service;


import burakkolay.vetproject.exception.PetNotFoundWithIdException;
import burakkolay.vetproject.repository.PetRepository;
import burakkolay.vetproject.model.DTO.PetDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.model.entity.Pet;
import burakkolay.vetproject.model.entity.Type;
import burakkolay.vetproject.model.mapper.PetMapper;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.Assert.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PetServiceTest {

    @Mock
    private PetRepository petRepository;

    @Mock
    private PetMapper petMapper;

    @Mock
    private OwnerService ownerService;

    @InjectMocks
    private PetService petService;


    @Test
    void getAllPets(){
        List<Pet> petList = getSamplePets();

        Mockito.when(petRepository.findAll()).thenReturn(petList);

        List<Pet> actualPetList=petService.getAllPets();

        Assert.assertEquals(petList.size(),actualPetList.size());

        actualPetList=actualPetList.stream().sorted(getPetComparator()).collect(Collectors.toList());
        petList=petList.stream().sorted(getPetComparator()).collect(Collectors.toList());

        for(int i=0;i<petList.size();i++){
            Pet p1=petList.get(i);
            Pet p2=actualPetList.get(i);

            Assert.assertEquals(p1.getAge(),p2.getAge());
            Assert.assertEquals(p1.getName(),p2.getName());
            Assert.assertEquals(p1.getOwner(),p2.getOwner());
        }
    }

    @Test
    void getById(){
        Pet pet1 = getSamplePets().get(1);
        Optional<Pet> optionalPet = Optional.of(pet1);

        Mockito.when(petRepository.findById(any())).thenReturn(optionalPet);

        Pet pet2 = petService.getById(1L);

        Assert.assertEquals(pet1.getAge(),pet2.getAge());
        Assert.assertEquals(pet1.getName(),pet2.getName());
        Assert.assertEquals(pet1.getOwner(),pet2.getOwner());
        Assert.assertEquals(pet1.getDescription(),pet2.getDescription());
        Assert.assertEquals(pet1.getType(),pet2.getType());
        Assert.assertEquals(pet1.getGenus(),pet2.getGenus());
    }

    @Test
    void getById_NOT_FOUND(){
        Mockito.when(petRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(PetNotFoundWithIdException.class,()->{
            Pet pet1 = petService.getById(any());
        });
    }


    @Ignore
    void create(){
        Pet expected = getSamplePets().get(0);
        expected.setId(null);
        Owner owner = new Owner(1L,"Ege","Coskun","050394793","egecosjun4@dsdq",getSamplePets());


        Mockito.when(petRepository.save(any())).thenReturn(expected);

        PetDTO petDTO = new PetDTO();
        petDTO.setName(expected.getName());
        petDTO.setDescription(expected.getDescription());
        petDTO.setType(expected.getType());
        petDTO.setGenus(expected.getGenus());
        petDTO.setAge(expected.getAge());

        Mockito.when(ownerService.getById(anyLong())).thenReturn(owner);
        Pet pet1 = petService.create(1L,petDTO);

        verify(petRepository,times(1)).save(expected);

       Assert.assertEquals(expected.getGenus(),pet1.getGenus());
       Assert.assertEquals(expected.getName(),pet1.getName());
       Assert.assertEquals(expected.getAge(),pet1.getAge());
       Assert.assertEquals(expected.getDescription(),pet1.getDescription());

    }

    @Test
    void delete(){
        Long petID = 1L;
        Pet pet = getSamplePets().get(0);


        doNothing().when(petRepository).deleteById(petID);

        petService.delete(1L);

        verify(petRepository,times(1)).deleteById(petID);
    }

    @Test
    void update(){
        Pet pet1 = getSamplePets().get(0);
        Pet updatedPet=new Pet(1L,"kek",Type.BIRD,"Rfk",14,"eges",null);

        Mockito.when(petRepository.findById(anyLong())).thenReturn(Optional.ofNullable(pet1));
        Mockito.when(petRepository.save(notNull())).thenAnswer(returnsFirstArg());

        petService.updatePet(anyLong(), petMapper.toDTO(updatedPet));




        Assert.assertEquals(pet1.getName(),updatedPet.getName());
        Assert.assertEquals(pet1.getAge(),updatedPet.getAge());
        Assert.assertEquals(pet1.getDescription(),updatedPet.getDescription());
    }




    private List<Pet> getSamplePets(){
        List<Pet> pets = new ArrayList<>();

        Pet pet = new Pet(1L,"King Charles", Type.DOG,"Roi", 1,"eges pet",null);
        Pet pet2 = new Pet(2L,"King Bob", Type.CAT,"Roik",  2,"eges pet",null);
        Pet pet3 = new Pet(3L,"King John", Type.FISH,"Bon",  3,"eges pet",null);
        Pet pet4 = new Pet(4L,"King Burk", Type.BIRD,"Ken",  6,"eges pet",null);

        pets.add(pet);
        pets.add(pet2);
        pets.add(pet3);
        pets.add(pet4);

        return pets;
    }

    private Comparator<Pet> getPetComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }


}
