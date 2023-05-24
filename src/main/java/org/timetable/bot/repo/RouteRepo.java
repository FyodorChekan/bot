package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.timetable.bot.model.Route;

import java.util.List;

public interface RouteRepo extends JpaRepository<Route, Integer> {

    Route findByNumber (Integer number);

    List<Route> findByDepartureAndArrival (String departure, String arrival);
}
