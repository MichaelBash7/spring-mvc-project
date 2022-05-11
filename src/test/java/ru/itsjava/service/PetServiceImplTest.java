package ru.itsjava.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.itsjava.domain.Pet;

import java.util.NoSuchElementException;


@DataJpaTest
@Import(PetServiceImpl.class)
public class PetServiceImplTest {
    @Autowired
    private PetService petService;

    @Test
    public void shouldHaveCorrectCreatePet(){
        var rat = new Pet(4L, "rat");
        petService.createPet(rat);
        var actualPet = petService.getPetById(4L);

        Assertions.assertEquals(rat, actualPet);
    }

    @Test
    public void shouldHaveCorrectUpdatePet(){
        var pet = petService.getPetById(1L);
        pet.setSpecies("rat");
        petService.updatePet(pet);
        var actualPet = petService.getPetById(1L);

        Assertions.assertEquals("rat", actualPet.getSpecies());
    }

    @Test
    public void shouldHaveCorrectDeletePet(){
        NoSuchElementException thrownException = Assertions.assertThrows(NoSuchElementException.class, ()-> {
            var otter = new Pet(5L, "otter");
            petService.deletePet(otter);
            var deletedPet = petService.getPetById(5L);
            Assertions.assertNull(deletedPet);
        });

        Assertions.assertEquals("No value present", thrownException.getMessage());
    }
}

