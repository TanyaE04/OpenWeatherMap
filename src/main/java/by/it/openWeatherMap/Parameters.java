package by.it.openWeatherMap;

import java.util.ResourceBundle;

public class Parameters {
    private static ResourceBundle bundle = ResourceBundle.getBundle("config");;
    public static final String URL = bundle.getString("api.request.url");
    public static final String APPID = bundle.getString("api.key");
    public static final String SCHEDULE = bundle.getString("schedule.cron");
    public static final String MONGODB = bundle.getString("mongodb.uri");
    public static final String DATABASE = bundle.getString("mongodb.database");
    public static final String COLLECTION = bundle.getString("mongodb.collection.name");
    public static final String ID = bundle.getString("mongodb.structure.id");
    public static final String DATE_RESPONSE = bundle.getString("mongodb.structure.response.date");
    public static final String STATUS_RESPONSE = bundle.getString("mongodb.structure.response.status");
    public static final String BODY_RESPONSE = bundle.getString("mongodb.structure.response.body");
}
