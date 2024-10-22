package com.example.bank.Repository;
import com.example.bank.models.operations;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface operationsRepository extends JpaRepository<operations, Long> {
}
