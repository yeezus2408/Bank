package com.example.bank.Repository;
import com.example.bank.models.operations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface operationsRepository extends JpaRepository<operations, Long> {
    List<operations> findAllById(long id);
    @Query("select c from operations c where c.fromUser = :id or c.toUser = :id")
    List<operations> findAllByWho(@Param("id") Long id);
}
