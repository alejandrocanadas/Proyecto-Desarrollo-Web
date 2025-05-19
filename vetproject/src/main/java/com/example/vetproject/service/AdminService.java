package com.example.vetproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.vetproject.entity.Admin;
import com.example.vetproject.repository.AdminRepository;

@Service
public interface AdminService {

    void add(Admin admin);
    
}
