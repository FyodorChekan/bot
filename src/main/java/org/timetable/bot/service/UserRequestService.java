package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.UserRequest;
import org.timetable.bot.repo.UserRequestRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserRequestService {

    private final UserRequestRepo userRequestRepo;

    public void create(UserRequest userRequest){
        userRequestRepo.save(userRequest);
    }

    public List<UserRequest> getUserRequestsByUserId(Integer userId){
        return userRequestRepo.findAllByUserId(userId);
    }

}
