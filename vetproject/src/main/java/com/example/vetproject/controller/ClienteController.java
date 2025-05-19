package com.example.vetproject.controller;

import java.sql.JDBCType;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import com.example.vetproject.entity.UserEntity;
import com.example.vetproject.DTOs.ClienteDTO;
import com.example.vetproject.DTOs.ClienteMapper;
import com.example.vetproject.entity.Cliente;
import com.example.vetproject.entity.LoginRequest;
import com.example.vetproject.error.NotFoundClientException;
import com.example.vetproject.service.ClienteService;
import com.example.vetproject.security.CustomUserDetailService;
import com.example.vetproject.security.JWTGenerator;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/clientes")
@CrossOrigin(origins = "http://localhost:4200") // Angular por defecto
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private CustomUserDetailService customUserDetailService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    JWTGenerator jwtGenerator;

    @GetMapping("/find/{id}")
    @Operation(summary = "Busca un cliente por id")
    public ResponseEntity<?> getCliente(@PathVariable Long id, Authentication authentication) {
        try {
            Cliente cliente = clienteService.SearchById(id);
            if (cliente == null) {
                throw new NotFoundClientException(id);
            }
            // Si el usuario es ADMIN, puede ver cualquier cliente
            if (authentication.getAuthorities().stream().anyMatch(a -> a.getAuthority().equals("ADMIN"))) {
                ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            }
            // Si es CLIENTE, solo puede ver su propio detalle
            if (authentication.getName().equals(cliente.getUser().getUsername())) {
                ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
                return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
            }
            // Si no es ni admin ni el dueño, acceso denegado
            return new ResponseEntity<>("No autorizado", HttpStatus.FORBIDDEN);
        } catch (NotFoundClientException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/all")
    @Operation(summary = "Busca todos los clientes")
    public ResponseEntity<List<ClienteDTO>> getAllClientes() {
        List<Cliente> clientes = clienteService.SearchAll();
        List<ClienteDTO> clientesDTO = clientes.stream()
                .map(ClienteMapper.INSTANCE::convert)
                .collect(Collectors.toList());
        return new ResponseEntity<>(clientesDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Elimina un cliente por id")
    public ResponseEntity<?> deleteCliente(@PathVariable Long id) {
        try {
            Cliente cliente = clienteService.SearchById(id);
            if (cliente == null) {
                throw new NotFoundClientException(id);
            }
            clienteService.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (NotFoundClientException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/update/{id}")
    @Operation(summary = "Actualiza un cliente por id")
    public ResponseEntity<?> updateCliente(@Valid @RequestBody Cliente cliente) {
        try {
            clienteService.update(cliente);
            ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
            return new ResponseEntity<>(clienteDTO, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al actualizar el cliente: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/add")
    @Operation(summary = "Agrega un nuevo cliente")
    public ResponseEntity<?> addCliente(@Valid @RequestBody Cliente cliente) {
        try {
            // Aquí puedes pedir la contraseña por el body o usar una por defecto
            String rawPassword = "cliente123"; // O cliente.getPassword() si lo recibes
            UserEntity userEntity = customUserDetailService.clienteToUser(cliente, rawPassword);
            cliente.setUser(userEntity);
            // Guarda primero el usuario si tu lógica lo requiere
            // userRepository.save(userEntity);
            clienteService.add(cliente);
            ClienteDTO clienteDTO = ClienteMapper.INSTANCE.convert(cliente);
            return new ResponseEntity<>(clienteDTO, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al crear el cliente: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/login")
    @Operation(summary = "Inicia sesión como cliente")
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
}
