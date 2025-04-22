package com.prab.security.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data // provides getters, setters, toString and hashcode methods. Also provides a required args constructor
//but this can be overwritten if other constructors are specified
@Builder //builder method

@NoArgsConstructor // just provides a none argument constructor
@AllArgsConstructor // provides an all argument constructor

@Entity //says that this is a data entity
@Table(name = "Users") // the table this class is linked to
public class Users implements UserDetails { // UserDetails is spring built in class. Can either implement it or extend the
    //built-in User class. This built in User class already implements UserDetails anyways. But by implemeting UserDetails ourselves
    //we have complete control over our object

    @Id
    @GeneratedValue
    private Integer id;

    private String firstname;

    private String lastname;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING) //tells Spring that this is an Enum, our custom Role enum
    private Role role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    //TODO-change the below later

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
