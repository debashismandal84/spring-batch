package com.example.batch.springbatch.jobs;

import com.example.batch.springbatch.models.UserDetails;
import com.example.batch.springbatch.workers.SimpleFileProcessor;
import com.example.batch.springbatch.workers.SimpleFileReader;
import com.example.batch.springbatch.workers.SimpleFileWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import javax.sql.DataSource;

@EnableBatchProcessing
@Configuration
public class SimpleFileProcessJob extends DefaultBatchConfigurer {

    @Autowired
    SimpleFileReader simpleFileReader;

    @Autowired
    SimpleFileProcessor simpleFileProcessor;

    @Autowired
    SimpleFileWriter simpleFileWriter;

    @Bean
    public Job fileProcessJob(JobBuilderFactory jobBuilderFactory,
                              StepBuilderFactory stepBuilderFactory
//                              ItemProcessor<UserDetails, UserDetails> userDetailsItemProcessor,
//                              ItemWriter<UserDetails> userDetailsItemWriter
) {

        Step step = stepBuilderFactory.get("file-load-step").<UserDetails, UserDetails>chunk(2)
                .reader(simpleFileReader)
               .processor(simpleFileProcessor)
                .writer(simpleFileWriter)
                .build();


        Job job = jobBuilderFactory.get("file-proc-job").incrementer(new RunIdIncrementer()).start(step)
                .build();
        return job;
    }


    @Override
    @Autowired
    @Lazy
    public void setDataSource( @Qualifier("getBatchDatabase") DataSource ds) {
        super.setDataSource(ds);
    }
}
