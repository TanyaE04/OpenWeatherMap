package by.it.open_weather_map;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class WeatherData {

    private int status;
    private Date date;
    private String body;

    public WeatherData() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(String d) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try {
            date = format.parse (d.substring (6));
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        this.date = date;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
