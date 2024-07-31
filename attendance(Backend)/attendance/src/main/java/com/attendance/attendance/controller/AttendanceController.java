//package com.attendance.attendance.controller;
//
//import com.attendance.attendance.service.AttendanceService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@CrossOrigin("*")
//@RequestMapping("/api/users/attendance")
//public class AttendanceController {
//    @Autowired
//    private AttendanceService attendanceService;
//
//
//
//    @PostMapping("/signin")
//    public ResponseEntity<String> signIn(@RequestParam String username) {
//        String result = attendanceService.signIn(username);
//        return ResponseEntity.ok(result);
//    }
//
//
//    @PostMapping("/signout")
//    public ResponseEntity<String> signOut(@RequestParam String username) {
//        String result = attendanceService.signOut(username);
//        return ResponseEntity.ok(result);
//    }
//}

package com.attendance.attendance.controller;

import com.attendance.attendance.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users/attendance")
public class AttendanceController {

    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/signin")
    public Map<String, String> signIn(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String message = attendanceService.signIn(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

    @PostMapping("/signout")
    public Map<String, String> signOut(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String message = attendanceService.signOut(username);
        Map<String, String> response = new HashMap<>();
        response.put("message", message);
        return response;
    }

//    @GetMapping("/status")
//    public Map<String, Boolean> getStatus(@RequestParam String username) {
//        boolean isSignedIn = attendanceService.hasSignedInToday(username);
//        Map<String, Boolean> response = new HashMap<>();
//        response.put("isSignedIn", isSignedIn);
//        return response;
//    }

    @GetMapping("/report")
    public Map<String, String> getReport(@RequestParam String username) {
        String report = attendanceService.getAttendanceReport(username);
        Map<String, String> response = new HashMap<>();
        response.put("report", report);
        return response;
    }

}


