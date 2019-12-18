package by.it.openWeatherMap;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.CronScheduleBuilder.*;
import static org.quartz.TriggerBuilder.newTrigger;

public class Main {
    public static void main(String[] args) {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.start();
            JobDetail job = newJob(HttpClient.class)
                    .withIdentity("myJob", "group1")
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("myTrigger", "group1")
                    .withSchedule(cronSchedule(Parameters.SCHEDULE))
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
