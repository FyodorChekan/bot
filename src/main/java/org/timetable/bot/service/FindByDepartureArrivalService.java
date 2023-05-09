package org.timetable.bot.service;

import java.util.Date;

public class FindByDepartureArrivalService {

    public static String find (String departure, String arrival, Date date) {
        String route = null;
        if(departure.equals("Moscow") && arrival.equals("Belgorod")){
            route = "Город отправления: " + departure + ". \nГород прибытия: " + arrival + ". \nДата: " + date;
        }
        return route;
    }
}
