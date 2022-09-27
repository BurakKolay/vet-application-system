package burakkolay.vetproject.repository;

import burakkolay.vetproject.model.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PetRepository extends JpaRepository<Pet,Long> {

    Optional<Pet> findByName(String name);


}
