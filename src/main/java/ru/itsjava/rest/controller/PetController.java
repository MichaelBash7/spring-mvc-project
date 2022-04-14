package ru.itsjava.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itsjava.rest.dto.PetDto;
import ru.itsjava.service.PetService;

@Controller
@RequiredArgsConstructor
public class PetController {
    private final PetService petService;

    @GetMapping("pet/{id}")
    public String getPage(@PathVariable("id") long id, Model model){
        model.addAttribute("pet", PetDto.toDto(petService.getPetById(id)));
        return "get-pet-page";
    }

    @GetMapping("pet/add")
    public String addPage(){
        return "add-pet-page";
    }

    @PostMapping("pet/add")
    public String afterAddPage(PetDto petDto){
        petService.createPet(PetDto.fromDto(petDto));
        return "redirect:/";
    }


}
