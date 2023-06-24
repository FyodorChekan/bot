package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.timetable.bot.model.UserRequest;

import java.util.List;

@Repository
public interface UserRequestRepo extends JpaRepository<UserRequest, Integer> {

    List<UserRequest> findAllByUserId(Integer userId);
}
