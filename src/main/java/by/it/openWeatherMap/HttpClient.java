package by.it.openWeatherMap;


import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public class HttpClient implements Job {

    public void getWeatherData() {

        int status = 0;
        String date = null;
        String body = null;

        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(Parameters.URL + Parameters.APPID);
        CloseableHttpResponse response;
        InputStream inputStream = null;
        try {
            response = httpClient.execute(httpGet);
            status = response.getStatusLine().getStatusCode();
            date = response.getFirstHeader("Date").toString();
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            StringBuilder stringBuilder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
            body = stringBuilder.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        MongoDB mongoDB = new MongoDB();
        mongoDB.saveInDB(status, date, body);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        getWeatherData();
    }
}
