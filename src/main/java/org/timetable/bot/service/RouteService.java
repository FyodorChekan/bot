package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.springframework.stereotype.Component;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

import java.util.List;

@Service
@Component
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepo routeRepo;

    public void create(Route route) {
        routeRepo.save(route);
    }

    public Route getRouteByNumber(Integer number) {
        return routeRepo.findByNumber(number);
    }

    public String getRoutesByDepartureAndArrival(String departure, String arrival) {
        List<Route> routeList = routeRepo.findByDepartureAndArrival(departure, arrival);
        StringBuilder routes = new StringBuilder();
        for (Route route: routeList) {
            routes.append(route.toString());
        }
        return routes.toString();
    }

    public boolean checkExistsRoute(String departure, String arrival) {
        List<Route> routeList = routeRepo.findByDepartureAndArrival(departure, arrival);
        return routeList != null;
    }
}
