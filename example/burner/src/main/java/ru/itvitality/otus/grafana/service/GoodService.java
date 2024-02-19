package ru.itvitality.otus.grafana.service;

import ru.itvitality.otus.grafana.model.Good;

import java.util.List;

public interface GoodService {
    List<Good> findAll();

    Good save(Good good);

    Good find(String id);
}
