package by.it.openWeatherMap;

import java.util.ResourceBundle;

public class Parameters {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");;
    public static final String URL = bundle.getString("api.request.url");
    public static final String APPID = bundle.getString("api.key");
    public static final String SCHEDULE = bundle.getString("schedule.cron");
    public static final String MONGODB = bundle.getString("mongodb.uri");
}
