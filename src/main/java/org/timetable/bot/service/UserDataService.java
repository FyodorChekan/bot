package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.UserData;
import org.timetable.bot.repo.UserDataRepo;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserDataService {

    private final UserDataRepo userDataRepo;

    public void create(UserData userData) {
        userDataRepo.save(userData);
    }

    public List<UserData> getAdmins() {
        return userDataRepo.findAllByIsAdmin(true);
    }

    public UserData getUserByLogin(String login) {
        return userDataRepo.findByLogin(login);
    }

    public boolean checkExistsUser(String login) {
        UserData userData = userDataRepo.findByLogin(login);
        return userData != null;
    }

    public boolean checkAdminUser(String login) {
        UserData userData = userDataRepo.findByLogin(login);
        return userData.isAdmin();
    }
}
