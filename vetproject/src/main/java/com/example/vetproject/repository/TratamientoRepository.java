package com.example.vetproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Tratamiento;

@Repository
public interface TratamientoRepository extends JpaRepository<Tratamiento, Long>{
    //
} 
