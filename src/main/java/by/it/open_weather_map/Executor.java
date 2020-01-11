package by.it.open_weather_map;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


import java.util.Date;


public class Executor implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        HttpClient httpClient = new HttpClient ();
        int status = httpClient.getStatus ();
        Date date = httpClient.getDate ();
        String bodyOfResponse = httpClient.getBody ();
        MongoDB.getInstance ().insertIntoDB (status, date, bodyOfResponse);
    }
}
