package com.chaitanya.airesumeanalyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.chaitanya.airesumeanalyzer.entity.Resume;

public interface ResumeRepository extends JpaRepository<Resume, Long> {

}