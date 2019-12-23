package by.it.openWeatherMap;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Executor implements Job {
    private int getResponseStatus (CloseableHttpResponse response){
        return response.getStatusLine().getStatusCode();
    }

    private Date getResponseDate (CloseableHttpResponse response){
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        String d = response.getFirstHeader("Date").toString();
        try {
            date = format.parse(d.substring(6));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    private String getWeatherData (CloseableHttpResponse response){
        StringBuilder stringBuilder = new StringBuilder();
        InputStream inputStream = null;
        try {
            inputStream = response.getEntity().getContent();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return stringBuilder.toString();
    }


        @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpClient httpClient = new HttpClient();
        CloseableHttpResponse response = httpClient.getResponse();
        int status = getResponseStatus(response);
        Date date = getResponseDate(response);
        String bodyOfResponse = getWeatherData(response);
        MongoDB.getInstance().insertIntoDB(status, date, bodyOfResponse);
    }
}
