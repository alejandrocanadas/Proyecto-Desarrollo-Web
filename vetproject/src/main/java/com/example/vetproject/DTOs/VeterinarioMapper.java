package com.example.vetproject.DTOs;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.vetproject.entity.Veterinario;

@Mapper
public interface VeterinarioMapper {
    VeterinarioMapper INSTANCE = Mappers.getMapper(VeterinarioMapper.class);
    
    VeterinarioDTO convert(Veterinario veterinario);
} 