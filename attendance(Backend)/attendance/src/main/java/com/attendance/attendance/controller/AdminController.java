package com.attendance.attendance.controller;

import com.attendance.attendance.model.Admin;
import com.attendance.attendance.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users/admins")
public class AdminController {

    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/{username}")
    public ResponseEntity<Admin> getAdminByUsername(@PathVariable String username) {
        Optional<Admin> admin = adminService.getAdminByUsername(username);
        if (admin.isPresent()) {
            return ResponseEntity.ok(admin.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> createAdmin(@RequestBody Admin admin) {
        if (adminService.adminExists(admin.getUsername())) {
            return ResponseEntity.status(409).body("This username is already registered.");
        }
        Admin savedAdmin = adminService.saveAdmin(admin);
        return ResponseEntity.ok("Admin registered successfully with username: " + savedAdmin.getUsername());
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginAdmin(@RequestBody Admin admin) {
        Optional<Admin> existingAdmin = adminService.getAdminByUsername(admin.getUsername());
        if (existingAdmin.isPresent() && existingAdmin.get().getPassword().equals(admin.getPassword())) {
            return ResponseEntity.ok("Login successful.");
        } else {
            return ResponseEntity.status(401).body("Invalid username or password.");
        }
    }

    @DeleteMapping("/{username}")
    public ResponseEntity<String> deleteAdminByUsername(@PathVariable String username) {
        if (adminService.adminExists(username)) {
            try {
                adminService.deleteAdminByUsername(username);
                return ResponseEntity.ok("Admin with username '" + username + "' deleted successfully.");
            } catch (Exception e) {
                return ResponseEntity.status(
                        HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to delete admin: " + e.getMessage());
            }
        } else {
            return ResponseEntity.notFound().build();
        }
    }



}

