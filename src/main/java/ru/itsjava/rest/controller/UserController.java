package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.itsjava.domain.User;
import ru.itsjava.rest.dto.UserDto;
import ru.itsjava.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/user")
    public String usersPage(Model model){
        List<UserDto> allUsersDto = userService.getAllUsers()
                .stream().map(UserDto::toDto).collect(Collectors.toList());

        model.addAttribute("users", allUsersDto);
        return "users-page";
    }

    @GetMapping("user/add")
    public String addUserPage() {
        return "add-user-page";
    }


    @PostMapping("user/add")
    public String afterAddUserPage(UserDto userDto) {
        if (UserDto.fromDto(userDto).getAge() <= 0 || UserDto.fromDto(userDto).getAge() >= 130){
            return "error-page";
        }
        userService.createUser(UserDto.fromDto(userDto));
        return "redirect:/user";
    }

    @GetMapping("user/{id}/edit")
    public String editUserPage(@PathVariable("id") long id, Model model) {
        User userById = userService.getUserById(id);
        model.addAttribute("userDto", UserDto.toDto(userById));
        return "edit-user-page";
    }

    @PostMapping("user/{id}/edit")
    public String afterEditUserPage(UserDto userDto) {
        if (UserDto.fromDto(userDto).getAge() <= 0 || UserDto.fromDto(userDto).getAge() >= 130){
            return "error-page";
        }
        userService.updateUser(UserDto.fromDto(userDto));
        return "redirect:/user";
    }

    @GetMapping("user/{id}/delete")
    public String deleteUserPage(@PathVariable("id") long id, Model model){
        User userById = userService.getUserById(id);
        model.addAttribute("userDto", UserDto.toDto(userById));
        return "delete-user-page";
    }

    @PostMapping("user/{id}/delete")
    public String afterDeleteUserPage(UserDto userDto) {
        userService.deleteUser(UserDto.fromDto(userDto));
        return "redirect:/user";
    }
}

