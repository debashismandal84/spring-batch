package com.example.batch.springbatch.workers;

import com.example.batch.springbatch.models.UserDetails;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class SimpleFileProcessor implements ItemProcessor<UserDetails, UserDetails> {

    private static Map<String, String> deptMapping = new HashMap<>(){{
        put("001","Technology");
        put("002","Account");
        put("003","Operation");
    }

    };

    @Override
    public UserDetails process(UserDetails userDetails) throws Exception {
        System.out.println("-- before dept change:"+userDetails);
        userDetails.setDept(deptMapping.get(userDetails.getDept()));
        System.out.println("-- After dept change:"+userDetails);
        return userDetails;
    }
}
