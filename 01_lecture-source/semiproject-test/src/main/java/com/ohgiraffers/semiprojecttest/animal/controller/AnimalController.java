package com.ohgiraffers.semiprojecttest.animal.controller;

import com.ohgiraffers.semiprojecttest.animal.model.dto.AnimalDTO;
import com.ohgiraffers.semiprojecttest.animal.model.service.AnimalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/animal/*")
public class AnimalController {

    private final AnimalService animalService;

    @Autowired
    public AnimalController(AnimalService animalService) {
        this.animalService = animalService;
    }

    @GetMapping("list")
    public String AllList(Model model) {

        List<AnimalDTO> list = animalService.AllList();

        model.addAttribute("list", list);

        return "/animal/list";
    }

    @PostMapping("find")
    public String FindList(@ModelAttribute AnimalDTO animalDTO) {

//        List<AnimalDTO> findList = animalService.findAnimal(animalDTO);

        return "/animal/find";
    }
}
