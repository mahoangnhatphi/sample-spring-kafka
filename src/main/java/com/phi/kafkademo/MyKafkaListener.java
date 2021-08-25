package com.phi.kafkademo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MyKafkaListener implements ApplicationRunner {

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	public void sendMessage(String msg) {
		kafkaTemplate.send("test", msg);
	}

	@KafkaListener(topics = "demo", groupId = "group-id")
	public void listen(String msg) {
		logger.debug("Received message in group - group-id: {}", msg);
	}
	
	@KafkaListener(topics = "test")
	public void listen1(String msg) {
		logger.info("Received message in group - group-id: {}", msg);
	}


	@Override
	public void run(ApplicationArguments args) throws Exception {
//		for (int i = 0; i < 1000; i++) {
//			sendMessage("Now: " + new Date());
//			Thread.sleep(2000);
//		}
	}
}
