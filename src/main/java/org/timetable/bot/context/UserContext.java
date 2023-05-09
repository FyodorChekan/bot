package org.timetable.bot.context;

import java.util.HashMap;
import java.util.Map;

public class UserContext {

    private static final Map<String, String> USER_STATE = new HashMap<>();


    public static void saveUserState(String command, String userName) {
        USER_STATE.put(userName, command);
    }

    public static String getUserState(String userName) {
        return USER_STATE.get(userName);
    }
}
