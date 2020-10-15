package com.bsuir.its.dao.repository;

import com.bsuir.its.dao.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface StatusRepository extends JpaRepository<Status,String> {
    Status findById(String id);
}
