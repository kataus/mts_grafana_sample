package ru.itvitality.otus.grafana.service;

import ru.itvitality.otus.grafana.model.Good;

public interface BurnerService {
    void burn();

    void process( Good good );
}
