package com.example.batch.springbatch.workers;

import com.example.batch.springbatch.models.UserDetails;
import org.springframework.batch.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SimpleFileWriter  implements ItemWriter<UserDetails> {
    @Override
    public void write(List<? extends UserDetails> list) throws Exception {
        System.out.println("in write - writing:"+list);
    }

    //spring.batch.job.enabled=false
}
