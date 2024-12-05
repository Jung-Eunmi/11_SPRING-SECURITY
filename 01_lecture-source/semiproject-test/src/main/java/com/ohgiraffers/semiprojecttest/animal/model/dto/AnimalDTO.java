package com.ohgiraffers.semiprojecttest.animal.model.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AnimalDTO {

    private String animalCode;
    private Date rescueDate1;
    private Date rescueDate2;
    private String rescueLocation;
    private int age;
    private String gender;
    private String animalStatus;
    private TypeDTO typeDTO;
    private BreedDTO breedDTO;
    private EmpDTO empDTO;

}
