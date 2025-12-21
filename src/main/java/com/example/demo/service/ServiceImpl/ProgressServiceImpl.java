package com.example.demo.service.impl;

import com.example.demo.model.Progress;
import com.example.demo.repository.ProgressRepository;
import com.example.demo.service.ProgressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProgressServiceImpl implements ProgressService {

    private final ProgressRepository repository;

    public ProgressServiceImpl(ProgressRepository repository) {
        this.repository = repository;
    }

    public Progress saveProgress(Progress progress) {
        return repository.save(progress);
    }

    public List<Progress> getAllProgress() {
        return repository.findAll();
    }
}
