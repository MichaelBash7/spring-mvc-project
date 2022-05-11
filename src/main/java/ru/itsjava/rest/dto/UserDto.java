package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String name;
    private String age;
    private String pet;

    public static User fromDto(UserDto userDto){
        if (userDto.id == null){
            userDto.id = "0";
        }
        long id = Long.parseLong(userDto.id);
        Pet pet = new Pet(0L, userDto.pet);
        int age = Integer.parseInt(userDto.age);

        return new User(id, userDto.name, age, pet);
    }

    public static UserDto toDto (User user){
        String id = String.valueOf(user.getId());
        String name = user.getName();
        String age = String.valueOf(user.getAge());
        String pet = user.getPet().getSpecies();

        return new UserDto(id, name, age, pet);

    }
}
