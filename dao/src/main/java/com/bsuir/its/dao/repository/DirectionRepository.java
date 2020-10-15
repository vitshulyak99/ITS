package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Direction;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DirectionRepository extends JpaRepository<Direction,String> {
    Direction findById(String id);
}
