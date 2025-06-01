package com.backendproject.backend_project.service;

import com.backendproject.backend_project.dto.PromptRequest;

public interface MentorService {
    public String getChatResponse(PromptRequest promptRequest);
}
