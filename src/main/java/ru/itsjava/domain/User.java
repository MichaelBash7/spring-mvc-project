package ru.itsjava.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private int age;

    @OneToOne(targetEntity = Pet.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private Pet pet;
}
