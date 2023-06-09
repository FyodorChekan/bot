package org.timetable.bot.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.timetable.bot.model.Route;

import java.util.Date;
import java.util.List;

@Repository
public interface RouteRepo extends JpaRepository<Route, Integer> {

    Route findByNumber (Integer number);

    List<Route> findByDepartureAndArrivalAndDate (String departure, String arrival, Date date);
}
