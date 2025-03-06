package com.example.roomly.repositories;

import com.example.roomly.Entities.room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface roomRepository extends JpaRepository<room, Long> {

}
