package ru.itvitality.otus.grafana.kafka.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import ru.itvitality.otus.grafana.kafka.ProcessingProducer;
import ru.itvitality.otus.grafana.model.Good;

@Service
@RequiredArgsConstructor
public class ProcessingProducerImpl implements ProcessingProducer {
    private String topic = "goods_topic";
    private final KafkaTemplate<String, Good> kafkaTemplate;

    @Override
    public void process( Good good ) {
        kafkaTemplate.send( topic, good );
    }
}
