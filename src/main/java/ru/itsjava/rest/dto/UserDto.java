package ru.itsjava.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import ru.itsjava.domain.Pet;
import ru.itsjava.domain.User;

import java.util.InputMismatchException;

@AllArgsConstructor
@Data
public class UserDto {
    private String id;
    private String name;
    private String age;
    private String pet;

    public static User fromDto(UserDto userDto) {
        if (userDto.id == null) {
            userDto.id = "0";
        }
        if (Integer.parseInt(userDto.age) <= 0 || Integer.parseInt(userDto.age) >= 130) {
            throw new InputMismatchException("Age input error!");
        }
        return new User(Long.parseLong(userDto.id), userDto.name,
                Integer.parseInt(userDto.age), new Pet(0L, userDto.pet));
    }

    public static UserDto toDto(User user) {
        String id = String.valueOf(user.getId());
        String name = user.getName();
        String age = String.valueOf(user.getAge());
        String pet = user.getPet().getSpecies();

        return new UserDto(id, name, age, pet);

    }
}
