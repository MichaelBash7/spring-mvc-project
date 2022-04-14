package ru.itsjava.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsjava.domain.Pet;
import ru.itsjava.repository.PetRepository;

@Service
@RequiredArgsConstructor
public class PetServiceImpl implements PetService{
    private final PetRepository petRepository;

    @Transactional
    @Override
    public void changePet(String oldSpecies, String updatedSpecies) {
        Pet pet = petRepository.getBySpecies(oldSpecies).get();
        pet.setSpecies(updatedSpecies);
        petRepository.save(pet);
        System.out.println("Successfully saved!");
    }

    @Transactional(readOnly = true)
    @Override
    public void printPet(String species) {
        Pet pet = petRepository.getBySpecies(species).get();
        System.out.println(pet);
    }

    @Transactional
    @Override
    public void createPet(Pet pet) {
        petRepository.save(pet);
    }

    @Transactional(readOnly = true)
    @Override
    public Pet getPetById(long id) {
        return petRepository.findById(id).get();
    }

    @Transactional
    @Override
    public void deletePetById(long id) {
        petRepository.deleteById(id);
    }
}
