package com.example.roomly.Entities.wsEntities;


import com.example.roomly.Entities.Enum.messageType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.awt.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class chatMessage {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String message;
    String Sender;
    LocalTime time = LocalTime.now();
    messageType type;

}


