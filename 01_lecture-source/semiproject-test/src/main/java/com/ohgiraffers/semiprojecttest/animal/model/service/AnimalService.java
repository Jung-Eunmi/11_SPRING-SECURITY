package com.ohgiraffers.semiprojecttest.animal.model.service;

import com.ohgiraffers.semiprojecttest.animal.model.dao.AnimalMapper;
import com.ohgiraffers.semiprojecttest.animal.model.dto.AnimalDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnimalService {

    private final AnimalMapper animalMapper;

    @Autowired
    public AnimalService(AnimalMapper animalMapper) {
        this.animalMapper = animalMapper;
    }

    public List<AnimalDTO> AllList() {
        return animalMapper.AllList();
    }



}
