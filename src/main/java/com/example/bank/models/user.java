package com.example.bank.models;

import com.example.bank.models.enums.roles;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "users")
public class user implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true, name = "name")
    @NotBlank(message = "Поле не может быть пустым!")
    private String username;

    @Column(name = "first_name")
    @NotBlank(message = "Поле не может быть пустым!")
    private String firstname;

    @Column(name = "last_name")
    @NotBlank(message = "Поле не может быть пустым!")
    private String lastname;

    @Column(name = "password")
    private String password;

    @Column(unique = true, name = "email")
    private String email;

    @Column(unique = true, name = "phone_number")
    @Size(min = 10, max = 11, message = "Неверный формат номера")
    private String phoneNumber;

    @Column(nullable = true ,unique = true, name = "number_agreement")
    private Integer agreement;

    @Column(nullable = false, name = "active")
    private boolean active;

    private LocalDate creationDate;

    @OneToMany(mappedBy = "owner")
    private List<card> cards;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;


    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<roles> roles = new HashSet<>();





    @PrePersist
    private void init(){
        creationDate = LocalDate.now();
    }





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
        return true; // Возвращает true, если пользователь авторизован
    }
}