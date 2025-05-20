package com.example.vetproject.DTOs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.mapstruct.Mapping;

import com.example.vetproject.entity.Cliente;
import java.util.List;

@Mapper
public interface ClienteMapper {
    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);
    
    @Mapping(target = "usuario", source = "user.username")
    @Mapping(target = "telefono", source = "telefono")
    ClienteDTO convert(Cliente cliente);

    List<MascotaDTO> mascotasToMascotaDTOs(java.util.List<com.example.vetproject.entity.Mascota> mascotas);
} 