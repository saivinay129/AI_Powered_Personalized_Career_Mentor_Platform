package com.backendproject.backend_project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.backend_project.component.JwtUtil;
import com.backendproject.backend_project.dto.AuthRequest;
import com.backendproject.backend_project.dto.AuthResponse;
import com.backendproject.backend_project.dto.PromptRequest;
import com.backendproject.backend_project.entity.User;
import com.backendproject.backend_project.repository.UserRepository;
import com.backendproject.backend_project.service.MentorService;

import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
public class MentorController {
    @Autowired
    private UserRepository userRepo;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private final MentorService mentorService;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    public MentorController(MentorService mentorService){
        this.mentorService=mentorService;
    }
    @PostMapping
    public String chat(@RequestBody PromptRequest promptRequest, HttpServletRequest request){
        return mentorService.getChatResponse(promptRequest,request);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody AuthRequest req) {
        if (userRepo.findByEmail(req.email()).isPresent()) {
            return ResponseEntity.badRequest().body("Email already in use");
        }
        User user = new User();
        user.setEmail(req.email());
        user.setPassword(passwordEncoder.encode(req.password()));
        userRepo.save(user);
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user.getEmail())));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        User user = userRepo.findByEmail(req.email())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(req.password(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(user.getEmail())));
    }
}
