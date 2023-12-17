package com.pack.authmicroservice.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import com.pack.authmicroservice.entity.UserEntity;

@Configuration
public class KafkaConsumerConfig {

	
	   @Bean
	   public ConsumerFactory<String, String> consumerFactory() {
	      Map<String, Object> props = new HashMap<>();
	      props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "PLAINTEXT://127.0.0.1:9092");
	      props.put(ConsumerConfig.GROUP_ID_CONFIG, "group-id");
	      props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
	      props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");
	    
	      
	      return new DefaultKafkaConsumerFactory<>(props, new StringDeserializer(),
	    		  new StringDeserializer());
	   }
	   
	  
	 
	   @Bean
	   public ConcurrentKafkaListenerContainerFactory<String,String> kafkaListenerContainerFactory() {
	      ConcurrentKafkaListenerContainerFactory<String,String> 
	      factory = new ConcurrentKafkaListenerContainerFactory<>();
	      factory.setConsumerFactory(consumerFactory());
	      return factory;
	   }
	   
	   
}
