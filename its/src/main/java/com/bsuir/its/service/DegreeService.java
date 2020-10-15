package com.bsuir.its.service;

import com.bsuir.its.dao.model.Degree;

import java.util.List;


public interface DegreeService {
    Degree getById(String id);
    List<Degree> getAll();
    Degree create(String name);
    void delete(String id);
    Degree update(Degree degree);
}
