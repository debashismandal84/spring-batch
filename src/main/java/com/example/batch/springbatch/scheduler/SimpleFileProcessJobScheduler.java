package com.example.batch.springbatch.scheduler;

import com.example.batch.springbatch.jobs.SimpleFileProcessJob;
import org.springframework.batch.core.*;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.batch.core.repository.dao.JobInstanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.annotation.Schedules;
import org.springframework.stereotype.Component;

@Component
public class SimpleFileProcessJobScheduler {

    @Scheduled(fixedDelay = 5000,initialDelay = 1000)
    private void launchJob(){
        System.out.println("job running through scheduler:"+this.getClass().getName());
        try {

            JobParameters params = new JobParametersBuilder()
                    .addString("JobID", String.valueOf(System.currentTimeMillis()))
                    .toJobParameters();

            JobParameters params2 = new JobParametersBuilder(jobExplorer).getNextJobParameters(abcJob).toJobParameters();
            jobLauncher.run(abcJob, params);
        } catch (
                JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (
                JobRestartException e) {
            e.printStackTrace();
        } catch (
                JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (
                JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    JobExplorer jobExplorer;
    @Autowired
    @Qualifier("fileProcessJob")
    private Job abcJob;

}
