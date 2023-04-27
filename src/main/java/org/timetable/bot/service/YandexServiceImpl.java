package org.timetable.bot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.Route;
import org.timetable.bot.model.UserData;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.repo.UserDataRepo;

@Service
@Profile("PROM")
public class YandexServiceImpl extends BaseYandexServiceImpl {

    protected YandexServiceImpl(RouteRepo repo) {
        super(repo);
    }

    @Override
    public Route requestYandex() {

        Route route = new Route(); // запрос в яндекс

        saveRoute(route);

        return route;
    }
}
