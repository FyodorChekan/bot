package org.timetable.bot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.Route;
import org.timetable.bot.model.UserData;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.repo.UserDataRepo;

import java.util.Date;

@Service
@Profile("DEV")
public class DevYandexServiceImpl extends BaseYandexServiceImpl{

    protected DevYandexServiceImpl(RouteRepo repo) {
        super(repo);
    }

    @Override
    public Route requestYandex() {
        Route route = new Route();
        route.setArrival("Moscow");
        route.setDeparture("Smolensk");
        route.setNumber(85);
        route.setCreate_request(new Date());

        saveRoute(route);

        return route;
    }
}
