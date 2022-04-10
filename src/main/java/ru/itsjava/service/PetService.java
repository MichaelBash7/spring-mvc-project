package ru.itsjava.service;

public interface PetService {

    void changePet(String oldSpecies, String updatedSpecies);

    void printPet (String species);
}
