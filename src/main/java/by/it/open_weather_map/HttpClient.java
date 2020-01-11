package by.it.open_weather_map;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class HttpClient {

    private int status;
    private Date date;
    private String body;

    public HttpClient() {
        URIBuilder uriBuilder = null;
        try {
            uriBuilder = new URIBuilder (Parameters.URL);
            uriBuilder.setParameter ("APPID", Parameters.APPID);
        } catch (URISyntaxException e) {
            e.printStackTrace ();
        }
        HttpGet httpGet = new HttpGet (String.valueOf (uriBuilder));
        try (CloseableHttpClient httpClient = HttpClients.createDefault ()) {

            CloseableHttpResponse response = httpClient.execute (httpGet);
            status = response.getStatusLine ().getStatusCode ();
            date = getResponseDate (response.getFirstHeader ("Date").toString ());
            body = getWeatherData (response.getEntity ().getContent ());
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

    private Date getResponseDate(String d) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        try {
            date = format.parse (d.substring (6));
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return date;
    }

    private String getWeatherData(InputStream inputStream) {
        StringBuilder stringBuilder = new StringBuilder ();
        try (BufferedReader bufferedReader = new BufferedReader (new InputStreamReader (inputStream))) {
            String line;
            while ((line = bufferedReader.readLine ()) != null) {
                stringBuilder.append (line);
            }
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return stringBuilder.toString ();
    }

    public String getBody() {
        return body;
    }


    public int getStatus() {
        return status;
    }

    public Date getDate() {
        return date;
    }
}
