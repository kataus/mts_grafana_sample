package ru.itvitality.otus.grafana.kafka;

import ru.itvitality.otus.grafana.model.Good;

public interface ProcessingProducer {
    void process( Good good );
}
