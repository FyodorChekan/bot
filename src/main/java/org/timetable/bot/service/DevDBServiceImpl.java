package org.timetable.bot.service;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.timetable.bot.model.Route;
import org.timetable.bot.repo.RouteRepo;

@Service
@Profile("DEV")
public class DevDBServiceImpl extends BaseDBServiceImpl {

    public DevDBServiceImpl(RouteRepo routeRepo){super(routeRepo);}

    @Override
    public Route findRoute(String number) {
        return find(number);
    }
}
