package by.it.open_weather_map;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URISyntaxException;


public class HttpClient {

    private Logger log = LogManager.getLogger(HttpClient.class.getName());

    public WeatherData getWeatherData() {
        WeatherData weatherData = new WeatherData ();
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder (Parameters.URL);
            uriBuilder.setParameter ("APPID", Parameters.APPID);
        } catch (URISyntaxException e) {
            log.debug ("Debug: in getWeatherData with URIBuilder");
        }
        HttpGet httpGet = new HttpGet (String.valueOf (uriBuilder));
        StringBuilder stringBuilder = new StringBuilder ();
        try (CloseableHttpClient httpClient = HttpClients.createDefault ()) {
            CloseableHttpResponse response = httpClient.execute (httpGet);
            weatherData.setStatus (response.getStatusLine ().getStatusCode ());
            weatherData.setDate (response.getFirstHeader ("Date").toString ());
            try (BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (response.getEntity ().getContent ()))) {
                String line;
                while ((line = bufferedReader.readLine ()) != null) {
                    stringBuilder.append (line);
                }
                weatherData.setBody (stringBuilder.toString ());
            }
        } catch (IOException e) {
            log.debug ("Debug: in getWeatherData while reading inputStream");
        }
        return weatherData;
    }
}
