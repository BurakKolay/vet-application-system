package burakkolay.vetproject.model.entity;


import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(updatable = false)
    private String genus;

    @Column(updatable = false)
    private Type type;
    private String name;
    private int age;
    private String description;


    @JoinColumn(name = "owner_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Owner owner;

}
