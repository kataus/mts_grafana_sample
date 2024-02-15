package ru.itvitality.otus.grafana.service;

import ru.itvitality.otus.grafana.model.dto.Good;

import java.util.List;

public interface GoodService {
    List<Good> list();

    Good save( Good good );

    Good find( String goodId );
}
