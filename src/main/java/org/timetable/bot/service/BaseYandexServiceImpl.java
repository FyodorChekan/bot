package org.timetable.bot.service;

import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;
import org.timetable.bot.repo.UserDataRepo;

public abstract class BaseYandexServiceImpl implements YandexService{

    private final RouteRepo repo;

    protected BaseYandexServiceImpl(RouteRepo repo) {
        this.repo = repo;
    }

    protected void saveRoute(Route route) {
        repo.save(route);
    }
}
