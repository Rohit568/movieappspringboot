package com.pack.authmicroservice.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

@Configuration
public class KafkaProducerConfig {
	   
	   String topic = "find_entity";
	   @Bean
	   public ProducerFactory<String, String> producerFactory() {
	      Map<String, Object> configProps = new HashMap<>();
	      configProps.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://127.0.0.1:9092");
	      configProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      configProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
	      return new DefaultKafkaProducerFactory<>(configProps);
	   }
	   @Bean
	   public KafkaTemplate<String, String> kafkaTemplate() {
	      return new KafkaTemplate<>(producerFactory());
	   }
	   
	   @Bean
	   public NewTopic topic(){
	    	return new NewTopic(topic,1,(short) 1);
	    }

}