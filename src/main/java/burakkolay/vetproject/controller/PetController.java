package burakkolay.vetproject.controller;


import burakkolay.vetproject.model.DTO.PetDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.model.entity.Pet;
import burakkolay.vetproject.service.OwnerService;
import burakkolay.vetproject.service.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pet")
public class PetController {

    private PetService petService;
    private OwnerService ownerService;


    public PetController(PetService petService, OwnerService ownerService) {
        this.petService = petService;
        this.ownerService = ownerService;
    }

    @GetMapping("/all")
    public ResponseEntity getAllPets(){
        List<Pet> allPets = petService.getAllPets();

        return ResponseEntity.ok(allPets);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody PetDTO petDTO,@PathVariable("id")Long id){
        Pet pet = petService.updatePet(id, petDTO);

        return ResponseEntity.ok("Related pet updated succesfully");
    }



    @GetMapping("/{id}")
    public ResponseEntity getPetById(@PathVariable("id")Long id){
        Pet byId = petService.getById(id);

        return ResponseEntity.ok(byId);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestParam Long id,@RequestBody PetDTO petDTO){
        Pet pet = petService.create(id,petDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(pet);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable("id")Long id){
        petService.delete(id);
        return ResponseEntity.ok("Related pet deleted succesfully.");
    }

    //-***************************************************************************-//

    @GetMapping("/showList")
    public ModelAndView showPetList(){
        ModelAndView mav = new ModelAndView("list-pets");
        mav.addObject("pets", petService.getAllPets());
        return mav;
    }

    @GetMapping("/showPetUpdateForm")
    public ModelAndView showPetUpdateForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("update-pet-form");
        Pet pet = petService.getById(id);
        mav.addObject("pet",pet);
        return mav;
    }


    @RequestMapping(path="/addPetForm")
    public ModelAndView createNewPetForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("create-pet-form");

        Pet pet = new Pet();
        Owner owner=ownerService.getById(id);

        mav.addObject("owner",owner);
        mav.addObject("pet",pet);
        return mav;
    }

    @RequestMapping(path="/savePet")
    public RedirectView savePet(@RequestParam Long id,@ModelAttribute PetDTO petDTO){
        petService.create(id,petDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
        return redirectView;
    }

    @RequestMapping(path = "/updatePetForm/{id}")
    public RedirectView updatePetForm(@PathVariable("id") Long id,@ModelAttribute PetDTO petDTO){
        petService.updatePet(id,petDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
        return redirectView;
    }

    @RequestMapping(path = "/deletePetForm")
    public RedirectView deletePetForm(@RequestParam Long id){
        petService.delete(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
        return redirectView;
    }

    @RequestMapping(path = "/showOwnerPetsForm")
    public ModelAndView showPetsOfOwner(@RequestParam Long id){
        Owner owner = ownerService.getById(id);
        List<Pet> petList = owner.getPet();
        ModelAndView mav = new ModelAndView("show-pets");
        mav.addObject("pets",petList);
        return mav;
    }


}
