package com.lynx.jobs.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import org.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import javax.activation.DataSource;

@Configuration
public class QuartzConfig {


    private String instanceName;


    private String instanceId;


    private String threadCount;


    private Long startDelay;


    private Long repeatInterval;


    private String description;


    private String key;

    @Autowired
    private DataSource dataSource;

    @Bean
    public org.quartz.spi.JobFactory jobFactory(ApplicationContext applicationContext) {

        QuartzJobFactory sampleJobFactory = new QuartzJobFactory();
        sampleJobFactory.setApplicationContext(applicationContext);
        return sampleJobFactory;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(ApplicationContext applicationContext) {

        SchedulerFactoryBean factory = new SchedulerFactoryBean();

        factory.setOverwriteExistingJobs(true);
        factory.setJobFactory(jobFactory(applicationContext));

        Properties quartzProperties = new Properties();
        quartzProperties.setProperty("org.quartz.scheduler.instanceName",instanceName);
        quartzProperties.setProperty("org.quartz.scheduler.instanceId",instanceId);
        quartzProperties.setProperty("org.quartz.threadPool.threadCount",threadCount);

        factory.setDataSource(dataSource);

        factory.setQuartzProperties(quartzProperties);
        factory.setTriggers(emailJobTrigger().getObject());

        return factory;
    }

    @Bean(name = "emailJobTrigger")
    public SimpleTriggerFactoryBean emailJobTrigger() {


        SimpleTriggerFactoryBean factoryBean = new SimpleTriggerFactoryBean();
        factoryBean.setJobDetail(emailJobDetails().getObject());
        factoryBean.setStartDelay(startDelay);
        factoryBean.setRepeatInterval(repeatInterval);
        factoryBean.setRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY);
        factoryBean.setMisfireInstruction(SimpleTrigger.MISFIRE_INSTRUCTION_RESCHEDULE_NEXT_WITH_REMAINING_COUNT);
        return factoryBean;
    }

    @Bean(name = "emailJobDetails")
    public JobDetailFactoryBean emailJobDetails() {

        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();
        jobDetailFactoryBean.setJobClass(EmailJob.class);
        jobDetailFactoryBean.setDescription(description);
        jobDetailFactoryBean.setDurability(true);
        jobDetailFactoryBean.setName(key);

        return jobDetailFactoryBean;
    }
}