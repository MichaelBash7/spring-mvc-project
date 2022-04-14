package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;

@AllArgsConstructor
@Data
public class PetDto {
    private String id;
    private String species;

    public static PetDto toDto(Pet pet){
        return new PetDto(String.valueOf(pet.getId()), pet.getSpecies());
    }

    public static Pet fromDto(PetDto petDto){
        if (petDto.id == null){
            petDto.id = "0";
        }
        return new Pet(Long.parseLong(petDto.id), petDto.getSpecies());
    }
}
