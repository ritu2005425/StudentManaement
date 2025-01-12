package com.example.kafkaconfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaTemplate;

//@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic topic(){
        return TopicBuilder
                .name("sampletopic")
                .build();
    }
    
//    public KafkaTemplate<String, Object>getKafkaTemplate()
//    {
//    	return new KafkaTemplate();
//    }
   
}
