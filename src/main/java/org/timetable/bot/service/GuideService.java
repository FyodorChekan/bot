package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.Guide;
import org.timetable.bot.repo.GuideRepo;

@Service
@Component
@RequiredArgsConstructor
public class GuideService {

    private final GuideRepo repo;

    public void create(Guide guide) {
        repo.save(guide);
    }

    public Guide getGuideByCity(String city) {
        return repo.findByCity(city);
    }
}
