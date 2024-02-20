package ru.itvitality.otus.grafana.kafka.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import ru.itvitality.otus.grafana.model.Good;
import ru.itvitality.otus.grafana.service.BurnerService;

@Slf4j
@Service
@RequiredArgsConstructor
public class GoodsListener {
    private final BurnerService burnerService;
    private ObjectMapper mapper = new ObjectMapper();

    @KafkaListener(topics = { "goods_topic" },
            concurrency = "2")
    @Timed
    public void receive( @Payload String rawData ) {
        try {
            var good = mapper.readValue( rawData, new TypeReference<Good>() {
            } );
            burnerService.process(good);
        } catch ( JsonProcessingException e ) {
            log.error( "receiver error", e );
        }
    }
}
