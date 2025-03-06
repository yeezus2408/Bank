package com.example.roomly.Entities;

import com.example.roomly.Entities.Enum.typeRoom;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class room {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    Integer number;

    @Enumerated(EnumType.STRING)
    typeRoom typeRoom;

    Integer capacity;

    String description;

    @OneToMany
    @JoinColumn(name = "services_id")
    List<Services> services;
}
