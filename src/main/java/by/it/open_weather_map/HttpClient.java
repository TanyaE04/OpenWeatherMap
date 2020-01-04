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


public class HttpClient {

    private CloseableHttpResponse response;
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

            response = httpClient.execute (httpGet);
            body = getWeatherData (response.getEntity ().getContent ());
        } catch (IOException e) {
            e.printStackTrace ();
        }
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

    public CloseableHttpResponse getResponse() {
        return response;
    }
}
