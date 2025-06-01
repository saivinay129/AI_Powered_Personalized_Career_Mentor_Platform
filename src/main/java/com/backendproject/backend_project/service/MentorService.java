package com.backendproject.backend_project.service;

import com.backendproject.backend_project.dto.PromptRequest;

import jakarta.servlet.http.HttpServletRequest;

public interface MentorService {
    public String getChatResponse(PromptRequest promptRequest,HttpServletRequest request1);
}
