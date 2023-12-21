package com.plantese.api.service;

import com.plantese.api.models.Admin;
import com.plantese.api.repository.IAdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    private final IAdminRepository adminRepository;

    public AdminService(IAdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public List<Admin> listar() {
        return this.adminRepository.findAll();
    }
}
