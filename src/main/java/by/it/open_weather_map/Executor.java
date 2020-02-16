package by.it.open_weather_map;


import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class Executor implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        MongoDB.getInstance ().insertIntoDB (new HttpClient().getWeatherData ());
    }
}
