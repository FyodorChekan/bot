package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepo routeRepo;

    public void create(Route route) {
        routeRepo.save(route);
    }

    public String getRoutesByDepartureAndArrivalAndDate(String departure, String arrival, String date) throws ParseException {
        DateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = date1.parse(date);
        List<Route> routeList = routeRepo.findByDepartureAndArrivalAndDate(departure, arrival, date2);
        StringBuilder routes = new StringBuilder();
        for (Route route: routeList) {
            routes.append(route.toString());
        }
        return routes.toString();
    }

    public boolean checkExistsRoute(String departure, String arrival, String date) throws ParseException {
        DateFormat date1 = new SimpleDateFormat("dd-MM-yyyy");
        Date date2 = date1.parse(date);
        List<Route> routeList = routeRepo.findByDepartureAndArrivalAndDate(departure, arrival, date2);
        return !routeList.isEmpty();
    }
}
