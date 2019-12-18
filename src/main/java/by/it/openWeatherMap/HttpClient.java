package by.it.openWeatherMap;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ResourceBundle;


public class HttpClient {
    private final static String APPID = "api.key";
    private final static String url = "api.request.url";

    public static void main(String[] args) {
        ResourceBundle bundle = ResourceBundle.getBundle("Configuration");
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(bundle.getString(url) + bundle.getString(APPID));
        CloseableHttpResponse response;
        InputStream inputStream = null;
        try {
            response = httpClient.execute(httpGet);
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
            }
            String json = stringBuilder.toString();
            System.out.println(json);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
