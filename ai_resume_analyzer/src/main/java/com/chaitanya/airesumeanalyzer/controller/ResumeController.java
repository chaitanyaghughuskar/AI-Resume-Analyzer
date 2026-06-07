package com.chaitanya.airesumeanalyzer.controller;

import java.io.IOException;
import com.chaitanya.airesumeanalyzer.dto.AIAnalysisResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.chaitanya.airesumeanalyzer.dto.ResumeTextResponse;
import com.chaitanya.airesumeanalyzer.entity.Resume;
import com.chaitanya.airesumeanalyzer.service.ResumeService;

import com.chaitanya.airesumeanalyzer.dto.JDMatchRequest;
import com.chaitanya.airesumeanalyzer.dto.JDMatchResponse;
import com.chaitanya.airesumeanalyzer.service.GroqService;

@RestController
@RequestMapping("/api/resume")
public class ResumeController {

    @Autowired
    private ResumeService resumeService;
     
    @Autowired
    private GroqService groqService;
    @PostMapping("/upload")
    public Resume uploadResume(@RequestParam("file") MultipartFile file)
            throws IOException {

        return resumeService.uploadResume(file);
    }

    @PostMapping("/extract")
    public ResumeTextResponse extractText(
            @RequestParam("file") MultipartFile file)
            throws IOException {

        return resumeService.extractText(file);
        
    }
    @PostMapping("/analyze")
    public AIAnalysisResponse analyzeResume(
            @RequestParam("file") MultipartFile file)
            throws Exception {
        return resumeService.analyzeResume(file);
    }
    @PostMapping("/match-jd")
    public JDMatchResponse matchJD(
            @RequestBody JDMatchRequest request)
            throws Exception {

        String result = groqService.matchJobDescription(
                request.getResumeText(),
                request.getJobDescription());

        JDMatchResponse response = new JDMatchResponse();

        response.setResult(result);

        return response;
    }
}