package burakkolay.vetproject.controller;


import burakkolay.vetproject.model.DTO.OwnerDTO;
import burakkolay.vetproject.model.entity.Owner;
import burakkolay.vetproject.repository.OwnerRepository;
import burakkolay.vetproject.service.OwnerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;

@RestController
@RequestMapping("/api/v1/owners")
public class OwnerController {

    private OwnerService ownerService;
    private OwnerRepository ownerRepository;

    public OwnerController(OwnerService ownerService, OwnerRepository ownerRepository) {
        this.ownerService = ownerService;
        this.ownerRepository = ownerRepository;
    }

    @GetMapping("/all")
    public ResponseEntity getAllOwners(){
        List<Owner> allOwners = ownerService.getAllOwners();
        return ResponseEntity.ok(allOwners);
    }


    @GetMapping("/{id}")
    public ResponseEntity getOwnerById(@PathVariable("id") Long id){
        Owner owner = ownerService.getById(id);

        return ResponseEntity.ok(owner);
    }
    @PostMapping("/create")
    public ResponseEntity create(@RequestBody OwnerDTO ownerDTO){
        Owner owner = ownerService.create(ownerDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(owner);
    }



    @PutMapping("/update/{id}")
    public ResponseEntity update(@RequestBody OwnerDTO ownerDTO,@PathVariable("id")Long id){
        Owner owner = ownerService.updateOwner(id, ownerDTO);

        return ResponseEntity.status(HttpStatus.OK).body("Related owner updated succesfully");
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity delete(@PathVariable(name="id")Long id){
        ownerService.delete(id);

        return ResponseEntity.ok("Related owner deleted succesfully");
    }

    //-******************************************************************************************************-//

    @GetMapping("/showList")
    public ModelAndView showOwnerList(){
        ModelAndView mav = new ModelAndView("list-owners");
        mav.addObject("owners", ownerService.getAllOwners());
        return mav;
    }

    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long id){
        ModelAndView mav = new ModelAndView("update-owner-form");
        Owner owner = ownerService.getById(id);
        mav.addObject("owner",owner);
        return mav;
    }

    @GetMapping("/addOwnerForm")
    public ModelAndView createNewCustomerForm(){
        ModelAndView mav = new ModelAndView("create-owner-form");
        Owner owner = new Owner();
        mav.addObject("owner", owner);
        return mav;
    }

    @PostMapping("/saveOwner")
    public RedirectView saveOwner(@ModelAttribute OwnerDTO owner){
        ownerService.create(owner);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
        return redirectView;
    }

    @RequestMapping(path = "/updateOwnerForm/{id}")
    public RedirectView  updateOwnerForm(@PathVariable("id") Long id,@ModelAttribute OwnerDTO ownerDTO){
        ownerService.updateOwner(id,ownerDTO);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
       // ModelAndView mav = new ModelAndView("list-owners");
        //mav.addObject("owners", ownerService.getAllOwners());
        return redirectView;
    }

    @RequestMapping(path = "/deleteOwnerForm")
    public RedirectView deleteOwnerForm(@RequestParam Long id){
        ownerService.delete(id);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://localhost:8093/api/v1/owners/showList");
        return redirectView;
    }



}
