package com.lynx.jobs.model;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class HttpJob implements Job {
    private String URL;

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println(URL);
    }

    public void setURL(String URL) {
        this.URL = URL;
    }
}