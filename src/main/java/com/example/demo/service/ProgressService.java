package com.example.demo.service;

import com.example.demo.model.Progress;
import java.util.List;

public interface ProgressService {
    Progress saveProgress(Progress progress);
    List<Progress> getAllProgress();
}
