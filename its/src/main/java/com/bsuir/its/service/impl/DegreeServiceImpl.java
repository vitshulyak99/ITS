package com.bsuir.its.service.impl;

import com.bsuir.its.dao.model.Degree;
import com.bsuir.its.dao.repository.DegreeRepository;
import com.bsuir.its.service.DegreeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class DegreeServiceImpl implements DegreeService {

    private final DegreeRepository degreeRepository;

    @Autowired
    public DegreeServiceImpl(DegreeRepository degreeRepository) {
        this.degreeRepository = degreeRepository;
    }

    @Override
    public Degree getById(String id) {
        return degreeRepository.findById(id);
    }

    @Override
    public List<Degree> getAll() {
        return degreeRepository.findAll();
    }

    @Override
    public Degree create(String name) {
        return degreeRepository.save(new Degree(name));
    }

    @Override
    public void delete(String id) {
        degreeRepository.delete(id);
    }

    @Override
    public Degree update(Degree degree) {
        return degreeRepository.save(degree);
    }
}
