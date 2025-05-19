package com.example.vetproject.security;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Admin;
import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.Rol;
import com.example.vetproject.entity.UserEntity;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.repository.RolRepository;
import com.example.vetproject.repository.UserRepository;
import org.springframework.security.core.userdetails.User;

@Service
public class CustomUserDetailService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        UserEntity userDB = userRepository.findByUsername(username).orElseThrow(
            () -> new UsernameNotFoundException("username not found")
        );

        UserDetails userDetails = new User(
            userDB.getUsername(),
            userDB.getPassword(),
            mapToGrantedAuthorities(userDB.getRoles())
        );
        return userDetails;
    }
    
    //UserEntity -> UserDetails(User estandar que usa spring security)
    private Collection<GrantedAuthority> mapToGrantedAuthorities(List<Rol> roles) {
        return roles.stream()
            .map(rol -> new SimpleGrantedAuthority(rol.getNombre()))
            .collect(Collectors.toList());
    }

    public UserEntity clienteToUser(Cliente cliente, String rawPassword) {
        UserEntity user = new UserEntity();
        user.setUsername(cliente.getEmail()); // O el campo que uses como username
        user.setPassword(passwordEncoder.encode(rawPassword));
        Rol rol = rolRepository.findByNombre("CLIENTE").orElseThrow();
        user.setRoles(List.of(rol));
        return user;
    }

    public UserEntity adminToUser(Admin admin, String rawPassword) {
        UserEntity user = new UserEntity();
        user.setUsername(admin.getEmail());
        user.setPassword(passwordEncoder.encode(rawPassword));
        Rol rol = rolRepository.findByNombre("ADMIN").orElseThrow();
        user.setRoles(List.of(rol));
        return user;
    }

    public UserEntity veterinarioToUser(Veterinario veterinario, String rawPassword) {
        UserEntity user = new UserEntity();
        user.setUsername(veterinario.getEmail());
        user.setPassword(passwordEncoder.encode(rawPassword));
        Rol rol = rolRepository.findByNombre("VETERINARIO").orElseThrow();
        user.setRoles(List.of(rol));
        return user;
    }
}
