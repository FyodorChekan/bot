package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.timetable.bot.model.UserData;

import java.util.List;

@Repository
public interface UserDataRepo extends JpaRepository<UserData, Integer> {

    UserData findByLogin(String login);

    List<UserData> findAllIsAdmin(boolean isAdmin);
}
