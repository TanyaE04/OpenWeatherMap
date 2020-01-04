package by.it.open_weather_map;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Parameters {

    private static Properties properties = new Properties ();

    static {
        try {
            properties.load (new FileInputStream (new File ("src\\main\\resources\\config.properties")));
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    public static final String URL = properties.getProperty ("api.request.url");
    public static final String APPID = properties.getProperty ("api.key");
    public static final String SCHEDULE = properties.getProperty ("schedule.cron");
    public static final String MONGODB = properties.getProperty ("mongodb.uri");
    public static final String DATABASE = properties.getProperty ("mongodb.database");
    public static final String COLLECTION = properties.getProperty ("mongodb.collection.name");
    public static final String ID = properties.getProperty ("mongodb.structure.id");
    public static final String DATE_RESPONSE = properties.getProperty ("mongodb.structure.response.date");
    public static final String STATUS_RESPONSE = properties.getProperty ("mongodb.structure.response.status");
    public static final String BODY_RESPONSE = properties.getProperty ("mongodb.structure.response.body");
}
