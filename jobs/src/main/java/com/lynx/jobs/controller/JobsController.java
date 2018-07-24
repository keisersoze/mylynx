package com.lynx.jobs.controller;

import com.lynx.jobs.model.HttpJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

@RestController
public class JobsController {

    @Autowired
    private Scheduler scheduler;

    @GetMapping("/jobs")
    public Set<JobKey> getJobKeys()  {
        try {
            return scheduler.getJobKeys(GroupMatcher.anyJobGroup());
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/jobs/{job_id}")
    public void deleteJob(@PathVariable(value = "job_id") String jobId)  {
        try {
            scheduler.deleteJob(JobKey.jobKey(jobId));
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/jobs/{job_id}")
    public List<? extends Trigger> getJob(@PathVariable(value = "job_id") String jobId)  {
        try {
            List<? extends Trigger> list = scheduler.getTriggersOfJob(JobKey.jobKey(jobId));
            return list;
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/jobs/interval")
    public void putJob(@RequestParam(value = "job_id") String jobId,
                       @RequestParam (value = "pipeline_execution_uri") String URL,
                       @RequestParam(value = "interval")  int interval,
                       @RequestParam(value = "n_times") Optional<Integer> nTimes
                       )  {

        JobDetail jobDetail = newJob(HttpJob.class)
                .withIdentity(jobId)
                .usingJobData("URL", URL)
                .build();

        SimpleScheduleBuilder scheduleBuilder = simpleSchedule()
                .withIntervalInSeconds(interval);
        if (nTimes.isPresent())
            scheduleBuilder.withRepeatCount(nTimes.get());
        else
            scheduleBuilder.repeatForever();

        Trigger trigger = newTrigger()
                .withIdentity("Trigger: " + jobId)
                .forJob(jobDetail)
                .withSchedule(scheduleBuilder)
                .build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/jobs/chron")
    public void putJob(@RequestParam(value = "job_id") String jobId,
                       @RequestParam (value = "pipeline_execution_uri") String URL,
                       @RequestParam (value = "") String xx
                       )  {

            JobDetail jobDetail = newJob(HttpJob.class)
                    .withIdentity(jobId)
                    .usingJobData("URL", URL)
                    .build();
            Trigger trigger = newTrigger()
                    .withIdentity("Trigger: "+jobId)
                    .forJob(jobDetail)
                    .withSchedule(simpleSchedule()
                            .withIntervalInSeconds(1)
                            .repeatForever()).build();
        try {
            scheduler.scheduleJob(jobDetail,trigger);
        } catch (SchedulerException e) {
            throw new RuntimeException(e);
        }
    }

}
