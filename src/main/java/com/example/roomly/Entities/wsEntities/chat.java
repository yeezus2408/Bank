package com.example.roomly.Entities.wsEntities;


import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.Entities.users;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class chat {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    @Column(nullable = false, unique = false, length = 50)
    String name;
    LocalTime updated;

    @ManyToMany
    @JoinTable(name = "chat_user",
        joinColumns = @JoinColumn(name="chat_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    List<users> participants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "chat_id")
    List<chatMessage> messages = new ArrayList<>();
}
