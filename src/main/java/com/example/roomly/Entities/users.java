package com.example.roomly.Entities;


import com.example.roomly.Entities.Enum.roles;
import com.example.roomly.Entities.wsEntities.chat;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class users implements UserDetails {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(nullable = false, unique = true)
    @Email
    String email;
    @Column(nullable = false)
    String firstname;
    @Column(nullable = false)
    String lastname;
    @Column(nullable = false)
    @Size(min = 8, max = 30)
    String password;
    @Column(nullable = false, name = "date_of_birth")
    LocalDate dateOfBirth;
    @Column(nullable = true, name = "age")
    @Min(18)
    Integer age;
    @Column(nullable = false, name = "gender")
    String gender;

    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = roles.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    Set<roles> roles = new HashSet<>();

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "chat_user",
        joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "chat_id",referencedColumnName = "id"))
    List<chat> chats = new ArrayList<>();


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean isAuthenticated() {
        return true;}
}
