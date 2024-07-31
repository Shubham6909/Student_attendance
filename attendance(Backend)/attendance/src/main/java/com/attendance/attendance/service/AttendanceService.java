//package com.attendance.attendance.service;
//
//import com.attendance.attendance.model.Attendance;
//import com.attendance.attendance.model.User;
//import com.attendance.attendance.repository.AttendanceRepository;
//import com.attendance.attendance.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDate;
//import java.time.LocalTime;
//import java.util.Optional;
//
//@Service
//public class AttendanceService {
//    @Autowired
//    private AttendanceRepository attendanceRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    public String signIn(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return "User not found";
//        }
//
//        LocalDate today = LocalDate.now();
//        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);
//
//        if (attendanceOpt.isPresent()) {
//            return "Already signed in today";
//        }
//
//        Attendance attendance = new Attendance();
//        attendance.setUser(user);
//        attendance.setDate(today);
//        attendance.setSignInTime(LocalTime.now());
//        attendanceRepository.save(attendance);
//
//        return "Signed in successfully";
//    }
//
//    public String signOut(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return "User not found";
//        }
//
//        LocalDate today = LocalDate.now();
//        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);
//
//        if (attendanceOpt.isEmpty()) {
//            return "No sign-in record found for today";
//        }
//
//        Attendance attendance = attendanceOpt.get();
//        if (attendance.getSignOutTime() != null) {
//            return "Already signed out today";
//        }
//
//        attendance.setSignOutTime(LocalTime.now());
//        attendanceRepository.save(attendance);
//
//        return "Signed out successfully";
//    }
//
//    public boolean hasSignedInToday(String username) {
//        User user = userRepository.findByUsername(username);
//        if (user == null) {
//            return false;
//        }
//
//        LocalDate today = LocalDate.now();
//        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);
//
//        return attendanceOpt.isPresent();
//    }
//}

package com.attendance.attendance.service;

import com.attendance.attendance.model.Attendance;
import com.attendance.attendance.model.User;
import com.attendance.attendance.repository.AttendanceRepository;
import com.attendance.attendance.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    public String signIn(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "User not found";
        }

        LocalDate today = LocalDate.now();
        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);

        if (attendanceOpt.isPresent()) {
            return "Already signed in today";
        }

        Attendance attendance = new Attendance();
        attendance.setUser(user);
        attendance.setDate(today);
        attendance.setSignInTime(LocalTime.now());
        attendanceRepository.save(attendance);

        return "Signed in successfully";
    }



    public String signOut(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "User not found";
        }

        LocalDate today = LocalDate.now();
        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);

        if (attendanceOpt.isEmpty()) {
            return "No sign-in record found for today";
        }

        Attendance attendance = attendanceOpt.get();
        if (attendance.getSignOutTime() != null) {
            return "Already signed out today";
        }

        attendance.setSignOutTime(LocalTime.now());
        attendanceRepository.save(attendance);

        return "Signed out successfully";
    }

    public boolean hasSignedInToday(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        LocalDate today = LocalDate.now();
        Optional<Attendance> attendanceOpt = attendanceRepository.findByUserAndDate(user, today);
        System.out.println(attendanceOpt.isPresent());
        return attendanceOpt.isPresent();
    }

    public String getAttendanceReport(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return "User not found";
        }

        List<Attendance> attendances = attendanceRepository.findByUser(user);
        StringBuilder report = new StringBuilder();
        for (Attendance attendance : attendances) {
            report.append("Date: ").append(attendance.getDate())
                    .append(", SignIn: ").append(attendance.getSignInTime())
                    .append(", SignOut: ").append(attendance.getSignOutTime())
                    .append("\n");
        }

        return report.toString();
    }

    public boolean deleteAttendanceByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            return false;
        }

        List<Attendance> attendances = attendanceRepository.findByUser(user);
        for (Attendance attendance : attendances) {
            attendanceRepository.delete(attendance);
        }

        return true;
    }
}
