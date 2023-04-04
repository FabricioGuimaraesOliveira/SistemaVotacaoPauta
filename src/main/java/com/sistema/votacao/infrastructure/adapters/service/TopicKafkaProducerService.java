package com.sistema.votacao.infrastructure.adapters.service;

import com.sistema.votacao.application.adapters.dto.response.PautaResponseDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TopicKafkaProducerService {

    @Value("${topic.name.producer}")
    private String topicName;

    private final KafkaTemplate<String, PautaResponseDTO> kafkaTemplate;

    public void sendTopicKafka(PautaResponseDTO pautaResponseDTO) {

        kafkaTemplate.send(topicName, pautaResponseDTO);
        log.info("Payload enviado para fila: {}", pautaResponseDTO.toString());
    }

}