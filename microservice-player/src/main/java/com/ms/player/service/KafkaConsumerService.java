package com.ms.player.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {
    @KafkaListener(topics = "team_topic", groupId = "player-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
        // Process the message here
    }
}