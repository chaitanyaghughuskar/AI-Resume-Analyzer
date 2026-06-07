package com.chaitanya.airesumeanalyzer.service;

import java.io.File;
import com.chaitanya.airesumeanalyzer.dto.AIAnalysisResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.chaitanya.airesumeanalyzer.dto.ResumeTextResponse;
import com.chaitanya.airesumeanalyzer.entity.Resume;
import com.chaitanya.airesumeanalyzer.repository.ResumeRepository;
import com.chaitanya.airesumeanalyzer.service.GroqService;

@Service
public class ResumeService {

    @Autowired
    private ResumeRepository resumeRepository;
    @Autowired
    private GroqService groqService;

    public Resume uploadResume(MultipartFile file) throws IOException {

        String fileName = file.getOriginalFilename();

        String uploadDir = System.getProperty("user.dir") + "/uploads/";

        File directory = new File(uploadDir);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        File destinationFile = new File(uploadDir + fileName);

        file.transferTo(destinationFile);

        Resume resume = new Resume();

        resume.setFileName(fileName);
        resume.setFilePath(destinationFile.getPath());
        resume.setUploadedAt(LocalDateTime.now());

        return resumeRepository.save(resume);
    }

    public ResumeTextResponse extractText(MultipartFile file) throws IOException {

        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);

        document.close();

        ResumeTextResponse response = new ResumeTextResponse();

        response.setFileName(file.getOriginalFilename());
        response.setContent(text);

        return response;
    }
    public AIAnalysisResponse analyzeResume(MultipartFile file)
            throws Exception{

        PDDocument document = Loader.loadPDF(file.getBytes());

        PDFTextStripper pdfStripper = new PDFTextStripper();

        String text = pdfStripper.getText(document);

        document.close();

        String analysis = groqService.analyzeResume(text);

        AIAnalysisResponse response = new AIAnalysisResponse();

        response.setFileName(file.getOriginalFilename());
        response.setAnalysis(analysis);

        return response;
        
    }
    
    
}