package org.timetable.bot.service.yandex;

import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

public abstract class BaseYandexServiceImpl implements YandexService {

    private final RouteRepo repo;

    protected BaseYandexServiceImpl(RouteRepo repo) {
        this.repo = repo;
    }

    protected void saveRoute(Route route) {
        repo.save(route);
    }
}
