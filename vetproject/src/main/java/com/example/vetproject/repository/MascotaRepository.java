package com.example.vetproject.repository;

import java.util.Collection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.vetproject.entity.Mascota;

@Repository
public interface MascotaRepository extends JpaRepository<Mascota, Long> { 

} 