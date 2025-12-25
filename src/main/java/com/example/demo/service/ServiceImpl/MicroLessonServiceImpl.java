package com.example.demo.service.impl;

import com.example.demo.model.MicroLesson;
import com.example.demo.service.MicroLessonService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MicroLessonServiceImpl implements MicroLessonService {

    // Temporary storage for demo
    private final List<MicroLesson> microLessons = new ArrayList<>();

    @Override
    public MicroLesson createMicroLesson(MicroLesson microLesson) {
        microLessons.add(microLesson);
        return microLesson;
    }

    @Override
    public MicroLesson getMicroLesson(Long id) {
        return microLessons.stream().filter(m -> m.getId().equals(id)).findFirst().orElse(null);
    }

    @Override
    public List<MicroLesson> getAllMicroLessons() {
        return microLessons;
    }

    @Override
    public MicroLesson updateMicroLesson(Long id, MicroLesson microLesson) {
        for (int i = 0; i < microLessons.size(); i++) {
            if (microLessons.get(i).getId().equals(id)) {
                microLessons.set(i, microLesson);
                return microLesson;
            }
        }
        return null;
    }

    @Override
    public void deleteMicroLesson(Long id) {
        microLessons.removeIf(m -> m.getId().equals(id));
    }
}
