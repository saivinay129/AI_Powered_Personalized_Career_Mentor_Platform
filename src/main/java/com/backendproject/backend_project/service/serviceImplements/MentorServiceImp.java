package com.backendproject.backend_project.service.serviceImplements;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestClient;

import com.backendproject.backend_project.component.JwtUtil;
import com.backendproject.backend_project.dto.Content;
import com.backendproject.backend_project.dto.GemininRequest;
import com.backendproject.backend_project.dto.GemininResponse;
import com.backendproject.backend_project.dto.Part;
import com.backendproject.backend_project.dto.PromptRequest;
import com.backendproject.backend_project.entity.CareerAdvice;
import com.backendproject.backend_project.entity.User;
import com.backendproject.backend_project.repository.CareerAdviceRepo;
import com.backendproject.backend_project.repository.UserRepository;
import com.backendproject.backend_project.service.MentorService;

import jakarta.servlet.http.HttpServletRequest;

public class MentorServiceImp implements MentorService{
    @Value("${spring.ai.openai.api-key}")
    private String key;
    @Value("${spring.ai.openai.chat.options.model}")
    private String model;
    private final RestClient restClient;
    private final CareerAdviceRepo careerAdviceRepo;
    @Autowired 
    private UserRepository userRepository;
    @Autowired 
    private JwtUtil jwtUtil;

    public MentorServiceImp(RestClient restClient,CareerAdviceRepo careerAdviceRepo){
        this.restClient = restClient;
        this.careerAdviceRepo =careerAdviceRepo;
    }

    @Override
    public String getChatResponse(PromptRequest promptRequest,HttpServletRequest request1) {
        String token = request1.getHeader("Authorization").substring(7);
        String email = jwtUtil.extractEmail(token);
        User user = userRepository.findByEmail(email).orElseThrow();
        String prompt = String.format("I want to become %s. I have the following skills: %s. What advice do you have for me?", 
                                       promptRequest.goal(), promptRequest.skills());
        String endpoint = String.format("/v1beta/models/%s:generateContent?key=%s", model,key);
        Part part = new Part(prompt);
        Content content = new Content("user", List.of(part));
        GemininRequest request = new GemininRequest(List.of(content));

        GemininResponse response = restClient.post()
        .uri(endpoint)
        .header("Content-Type", "application/json")
        .body(request)
        .retrieve()
        .body(GemininResponse.class);

        String advice = response.getCandidates().get(0).getContent().getParts().get(0).getText();

        CareerAdvice record = new CareerAdvice();
        record.setGoal(promptRequest.goal());
        record.setSkills(promptRequest.skills());
        record.setAdvice(advice);
        record.setCreatedAt(LocalDateTime.now());
        record.setUser(user);
        careerAdviceRepo.save(record);
        return advice;
    }

}
