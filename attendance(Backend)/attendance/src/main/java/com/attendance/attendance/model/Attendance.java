package com.attendance.attendance.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"username", "date"})})
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "username", referencedColumnName = "username", nullable = false)
    private User user;

    @Column(nullable = false)
    private LocalDate date;

    private LocalTime signInTime;
    private LocalTime signOutTime;

    // Default constructor
    public Attendance() {}

    // Parameterized constructor
    public Attendance(User user, LocalDate date, LocalTime signInTime, LocalTime signOutTime) {
        this.user = user;
        this.date = date;
        this.signInTime = signInTime;
        this.signOutTime = signOutTime;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(LocalTime signInTime) {
        this.signInTime = signInTime;
    }

    public LocalTime getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(LocalTime signOutTime) {
        this.signOutTime = signOutTime;
    }
}