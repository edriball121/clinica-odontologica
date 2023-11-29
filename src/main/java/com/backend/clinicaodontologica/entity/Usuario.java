package com.backend.clinicaodontologica.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.stream.Collectors;

import static org.apache.catalina.realm.UserDatabaseRealm.getRoles;

@Entity
public class Usuario implements UserDetails {
    @Id
    private Long id;
    private String password;
    private String username;

    // Atributos y otros detalles de la entidad...

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Devuelve los roles del usuario como GrantedAuthority
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.getClass()))
                .collect(Collectors.toList());
    }

    private Collection<Object> getRoles() {
        return null;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
    
}