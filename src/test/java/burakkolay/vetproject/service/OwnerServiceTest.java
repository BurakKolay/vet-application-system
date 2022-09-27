package burakkolay.vetproject.service;


import burakkolay.vetproject.repository.OwnerRepository;
import burakkolay.vetproject.exception.OwnerNotFoundException;
import burakkolay.vetproject.model.DTO.OwnerDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.model.mapper.OwnerMapper;
import org.junit.Assert;
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

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.AdditionalAnswers.returnsFirstArg;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OwnerServiceTest {

    @Mock
    private OwnerRepository ownerRepository;

    @Mock
    private OwnerMapper ownerMapper;

    @InjectMocks
    private OwnerService ownerService;


    @Test
    void getAllOwners(){
        List<Owner> list = getSampleOwners();

        Mockito.when(ownerRepository.findAll()).thenReturn(list);

        List<Owner> actualList=ownerService.getAllOwners();

        Assert.assertEquals(actualList.size(),list.size());

        actualList=actualList.stream().sorted(getOwnerComparator()).collect(Collectors.toList());
        list=list.stream().sorted(getOwnerComparator()).collect(Collectors.toList());

        for(int i=0;i<actualList.size();i++){
            Owner owner1 = actualList.get(0);
            Owner owner2 = list.get(0);

            Assert.assertEquals(owner1.getFirstName(),owner2.getFirstName());
            Assert.assertEquals(owner1.getMail(),owner2.getMail());
            Assert.assertEquals(owner1.getLastName(),owner2.getLastName());
            Assert.assertEquals(owner1.getPhoneNumber(),owner2.getPhoneNumber());
        }
    }

    private List<Owner> getSampleOwners(){
        List<Owner> list = new ArrayList<>();
        Owner owner = new Owner(1L,"Ege","Coskun","05105105","egecsojcm@mail",null);
        Owner owner2 = new Owner(2L,"Burak","Kolay","05390839","53415@mail",null);
        Owner owner3 = new Owner(3L,"Mehmet","Ali","05105105","531515@mail",null);

        list.add(owner);
        list.add(owner2);
        list.add(owner3);

        return list;
    }

    @Test
    void getById(){
        Owner owner = getSampleOwners().get(1);
        Optional<Owner> optionalOwner = Optional.of(owner);

        Mockito.when(ownerRepository.findById(any())).thenReturn(optionalOwner);

        Owner owner2 = ownerService.getById(1L);

        Assert.assertEquals(owner.getPhoneNumber(),owner2.getPhoneNumber());
        Assert.assertEquals(owner.getMail(),owner2.getMail());
        Assert.assertEquals(owner.getLastName(),owner2.getLastName());
        Assert.assertEquals(owner.getFirstName(),owner2.getFirstName());
    }

    @Test
    void getById_NOT_FOUND() {
        Mockito.when(ownerRepository.findById(any())).thenReturn(Optional.empty());

        assertThrows(OwnerNotFoundException.class,
                () -> {
                    Owner owner = ownerService.getById(any());
                }
        );
    }

    @Test
    void create(){
        Owner expected = getSampleOwners().get(0);
        expected.setId(null);

        Mockito.when(ownerRepository.save(any())).thenReturn(expected);

        OwnerDTO ownerDTO = new OwnerDTO();
        ownerDTO.setPhoneNumber(expected.getPhoneNumber());
        ownerDTO.setLastName(expected.getLastName());
        ownerDTO.setMail(expected.getMail());
        ownerDTO.setFirstName(expected.getFirstName());

        Owner actualOwner = ownerService.create(ownerDTO);

        verify(ownerRepository,times(1)).save(expected);

        Assert.assertEquals(expected.getFirstName(),actualOwner.getFirstName());
        Assert.assertEquals(expected.getPhoneNumber(),actualOwner.getPhoneNumber());
        Assert.assertEquals(expected.getLastName(),actualOwner.getLastName());
    }

    @Test
    void delete() {
        Long ownerID = 1L;
        Owner owner = getSampleOwners().get(0);



        doNothing().when(ownerRepository).deleteById(ownerID);

        //validate step
        ownerRepository.deleteById(1L);

        verify(ownerRepository, times(1)).deleteById(ownerID);
    }

    @Test
    void update(){
        Owner owner = getSampleOwners().get(0);
        Owner updatedOwner=new Owner(1L,"Eqe","Qoskn","121512934","mail.com",null);

        Mockito.when(ownerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(owner));
        Mockito.when(ownerRepository.save(notNull())).thenAnswer(returnsFirstArg());

        ownerService.updateOwner(anyLong(), ownerMapper.toDTO(updatedOwner));


        Assert.assertEquals(owner.getLastName(),updatedOwner.getLastName());
        Assert.assertEquals(owner.getMail(),updatedOwner.getMail());
        Assert.assertEquals(owner.getFirstName(),updatedOwner.getFirstName());
    }

    private Comparator<Owner> getOwnerComparator() {
        return (o1, o2) -> {
            if (o1.getId() - o2.getId() < 0)
                return -1;
            if (o1.getId() - o2.getId() == 0)
                return 0;
            return 1;
        };
    }

}
