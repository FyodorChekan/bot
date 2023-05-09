package org.timetable.bot.service;

import org.timetable.bot.model.Route;

public interface DBService {

    Route findRoute(String number);
}
