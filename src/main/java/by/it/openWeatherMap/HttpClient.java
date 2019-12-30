package by.it.openWeatherMap;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;


public class HttpClient {

    public CloseableHttpResponse getResponse() {

        HttpGet httpGet = new HttpGet (Parameters.URL + Parameters.APPID);
        CloseableHttpResponse response = null;
        try (CloseableHttpClient httpClient = HttpClients.createDefault ()) {
            response = httpClient.execute (httpGet);
        } catch (IOException e) {
            e.printStackTrace ();
        }
        return response;
    }
}
