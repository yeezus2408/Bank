package com.example.dnsDB.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.management.relation.Role;
import java.time.LocalDate;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
@Table(name = "users")
public class user implements UserDetails {
    @Id
    @GeneratedValue
    private Long id;
    @Column(unique = true, nullable = false, name = "name")
    private String username;
    @Column(nullable = false, name = "password")
    private String password;
    @Column(unique = true, nullable = false, name = "email")
    private String email;
    @Column(unique = true, nullable = false, name = "phone_number")
    private String phoneNumber;
    @Column(unique = true, nullable = false, name = "number_agreement")
    private String agreement;
    @Column(nullable = false, name = "active")
    private boolean active;
    private LocalDate creationDate;

    @CollectionTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    private Set<com.example.dnsDB.models.enums.roles> roles = new HashSet<>();

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
}
