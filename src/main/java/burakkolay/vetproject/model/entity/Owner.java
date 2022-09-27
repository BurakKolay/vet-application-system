package burakkolay.vetproject.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "owner")
public class Owner {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String mail;

    @OneToMany(fetch = LAZY,mappedBy = "owner",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private List<Pet> pet;




}
