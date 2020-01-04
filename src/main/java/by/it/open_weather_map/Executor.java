package by.it.open_weather_map;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Executor implements Job {
    private int getResponseStatus(CloseableHttpResponse response) {
        return response.getStatusLine ().getStatusCode ();
    }

    private Date getResponseDate(CloseableHttpResponse response) {
        Date date = null;
        SimpleDateFormat format = new SimpleDateFormat ("EEE, d MMM yyyy HH:mm:ss Z", Locale.ENGLISH);
        String d = response.getFirstHeader ("Date").toString ();
        try {
            date = format.parse (d.substring (6));
        } catch (ParseException e) {
            e.printStackTrace ();
        }
        return date;
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpClient httpClient = new HttpClient ();
        CloseableHttpResponse response = httpClient.getResponse ();
        int status = getResponseStatus (response);
        Date date = getResponseDate (response);
        String bodyOfResponse = httpClient.getBody ();
        MongoDB.getInstance ().insertIntoDB (status, date, bodyOfResponse);
    }
}
