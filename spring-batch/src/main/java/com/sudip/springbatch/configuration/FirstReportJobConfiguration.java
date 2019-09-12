package com.sudip.springbatch.configuration;

import com.sudip.springbatch.tasks.FirstTask;
import com.sudip.springbatch.tasks.SecondTask;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FirstReportJobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;


    @Bean
    public Step firstStep(){
        return stepBuilderFactory.get("firstStep")
                .tasklet(getFirstTaskLet())
                .build();
    }

    @Bean
    public Step secondStep() {
        return stepBuilderFactory.get("secondTask")
                .tasklet(getSecondTaskLet())
                .build();
    }

    @Bean
    public Tasklet getFirstTaskLet() {
        return new FirstTask();
    }

    @Bean
    public Tasklet getSecondTaskLet() {
        return new SecondTask();
    }

    @Bean
    public Job runningJob(){

        return jobBuilderFactory.get("runningJob").start(firstStep()).next(secondStep()).build();
    }

}

