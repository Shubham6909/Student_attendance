package com.attendance.attendance.service;

import com.attendance.attendance.model.Admin;
import com.attendance.attendance.model.User;
import com.attendance.attendance.repository.AdminRepository;
import com.attendance.attendance.repository.AttendanceRepository;
import com.attendance.attendance.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    @Autowired
    private final AdminRepository adminRepository;

    @Autowired
    private final UserRepository userRepository;

    @Autowired
    private final AttendanceRepository attendanceRepository;

    @Autowired
    public AdminService(AdminRepository adminRepository, UserRepository userRepository, AttendanceRepository attendanceRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.attendanceRepository = attendanceRepository;
    }

    public Optional<Admin> getAdminByUsername(String username) {
        return adminRepository.findByUsername(username);
    }

    public boolean adminExists(String username) {
        return adminRepository.existsByUsername(username);
    }
    @Transactional
    public void deleteAdminByUsername(String username) {
        adminRepository.deleteByUsername(username);
    }

    public Admin saveAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
