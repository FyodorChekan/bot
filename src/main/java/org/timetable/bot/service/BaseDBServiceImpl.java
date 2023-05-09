package org.timetable.bot.service;

import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

public abstract class BaseDBServiceImpl implements DBService{

    private final RouteRepo routeRepo;

    protected BaseDBServiceImpl(RouteRepo routeRepo) {
        this.routeRepo = routeRepo;
    }

    protected Route find(String number){
        Integer num = Integer.parseInt(number);

        return routeRepo.findByNumber(num);
    }
}
