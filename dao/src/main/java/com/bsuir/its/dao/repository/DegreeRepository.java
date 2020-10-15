package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Degree;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DegreeRepository extends JpaRepository<Degree,String> {
    Degree findById(String id);
}
