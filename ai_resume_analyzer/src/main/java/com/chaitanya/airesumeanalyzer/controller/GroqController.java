package com.chaitanya.airesumeanalyzer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chaitanya.airesumeanalyzer.service.GroqService;

@RestController
@RequestMapping("/api/groq")
public class GroqController {

    @Autowired
    private GroqService groqService;

    @PostMapping("/test")
    public String test(@RequestBody String text) throws Exception {

        return groqService.analyzeResume(text);
    }
}