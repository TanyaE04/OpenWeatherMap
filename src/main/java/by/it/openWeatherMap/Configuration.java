package by.it.openWeatherMap;

import java.util.ListResourceBundle;

public class Configuration extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][] {
            {"api.request.url","http://api.openweathermap.org/data/2.5/weather?q=Minsk&APPID="},
            {"api.key", "9024b7397cf770c969fbe6382419587e"},
        };
    }
}
