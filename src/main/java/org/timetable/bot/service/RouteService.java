package org.timetable.bot.service;

import lombok.RequiredArgsConstructor;
import org.jvnet.hk2.annotations.Service;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

@Service
@RequiredArgsConstructor
public class RouteService {

    private final RouteRepo routeRepo;

    public void create(Route route){
        routeRepo.save(route);
    }

    public Route getRouteByNumber(Integer number){
        return routeRepo.findByNumber(number);
    }

    public boolean checkExistsRoute(Integer number){
        Route route = routeRepo.findByNumber(number);
        return route != null;
    }


}
