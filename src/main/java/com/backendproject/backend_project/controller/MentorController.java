package com.backendproject.backend_project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendproject.backend_project.dto.PromptRequest;
import com.backendproject.backend_project.service.MentorService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/chat")
public class MentorController {
    private final MentorService mentorService;
    public MentorController(MentorService mentorService){
        this.mentorService=mentorService;
    }
    @PostMapping
    public String chat(@RequestBody PromptRequest promptRequest){
        return mentorService.getChatResponse(promptRequest);
    }
}
