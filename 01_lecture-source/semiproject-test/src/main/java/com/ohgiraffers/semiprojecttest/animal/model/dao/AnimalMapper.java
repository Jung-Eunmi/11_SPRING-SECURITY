package com.ohgiraffers.semiprojecttest.animal.model.dao;

import com.ohgiraffers.semiprojecttest.animal.model.dto.AnimalDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AnimalMapper {

    List<AnimalDTO> AllList();
}
