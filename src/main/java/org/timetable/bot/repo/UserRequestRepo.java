package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.timetable.bot.model.UserRequest;

import java.util.List;

public interface UserRequestRepo extends JpaRepository<UserRequest, Integer> {

    List<UserRequest> findAllByUserId(Integer userId);
}
