package com.example.vetproject.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.example.vetproject.DTOs.VeterinarioDTO;
import com.example.vetproject.DTOs.VeterinarioMapper;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.entity.Tratamiento;
import com.example.vetproject.entity.UserEntity;
import com.example.vetproject.entity.Veterinario;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.security.CustomUserDetailService;
import com.example.vetproject.security.JWTGenerator;
import com.example.vetproject.service.VeterinarioService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/veterinario")
@CrossOrigin(origins = "http://localhost:4200")
public class VeterinarioController {
    @Autowired
    VeterinarioService veterinarioService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca un veterinario por id")
    public ResponseEntity<?> getVeterinario(@PathVariable Long id) {
        try {
            Veterinario veterinario = veterinarioService.SearchById(id);
            if (veterinario == null) {
                throw new NotFoundClientException(id);
            }
            VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(veterinario);
            return new ResponseEntity<>(veterinarioDTO, HttpStatus.OK);
        } catch (NotFoundClientException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("tratamientos/{id}")
    @Operation(summary = "Obtiene todos los tratamientos asignados a un veterinario")
    public ResponseEntity<?> getTratamientosVeterinario(@PathVariable Long id) {
        try {
            Veterinario veterinario = veterinarioService.SearchById(id);
            if (veterinario == null) {
                throw new NotFoundClientException(id);
            }
            List<Tratamiento> tratamientos = veterinario.getTratamientos();
            return new ResponseEntity<>(tratamientos, HttpStatus.OK);
        } catch (NotFoundClientException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión como veterinario")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequest.getUsuario(),
                            loginRequest.getContrasena()));
            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtGenerator.generateToken(authentication);

            return new ResponseEntity<String>(token, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Usuario o contraseña incorrectos", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping
    @Operation(summary = "Obtiene todos los veterinarios")
    public ResponseEntity<List<VeterinarioDTO>> getAllVeterinarios() {
        try {
            List<Veterinario> veterinarios = veterinarioService.findAll();
            List<VeterinarioDTO> veterinariosDTO = veterinarios.stream()
                    .map(VeterinarioMapper.INSTANCE::convert)
                    .collect(Collectors.toList());
            return new ResponseEntity<>(veterinariosDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    @Operation(summary = "Crea un nuevo veterinario")
    public ResponseEntity<?> addVeterinario(@RequestBody Veterinario veterinario) {
        try {
            String rawPassword = "veterinario123";
            UserEntity userEntity = customUserDetailService.veterinarioToUser(veterinario, rawPassword);
            veterinario.setUser(userEntity);
            veterinarioService.add(veterinario);
            VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(veterinario);
            return new ResponseEntity<>(veterinarioDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el veterinario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{id}")
    @Operation(summary = "Actualiza un veterinario existente")
    public ResponseEntity<?> updateVeterinario(@PathVariable Long id,
            @RequestBody Veterinario veterinario) {
        try {
            Veterinario updatedVeterinario = veterinarioService.update(id, veterinario);
            if (updatedVeterinario != null) {
                VeterinarioDTO veterinarioDTO = VeterinarioMapper.INSTANCE.convert(updatedVeterinario);
                return new ResponseEntity<>(veterinarioDTO, HttpStatus.OK);
            }
            return new ResponseEntity<>("Veterinario no encontrado", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el veterinario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Elimina un veterinario")
    public ResponseEntity<?> deleteVeterinario(@PathVariable Long id) {
        try {
            veterinarioService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar el veterinario: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
