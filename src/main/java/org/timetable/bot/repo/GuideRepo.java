package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.timetable.bot.model.Guide;

@Repository
public interface GuideRepo extends JpaRepository<Guide, Integer> {
    Guide findByCity(String city);
}
