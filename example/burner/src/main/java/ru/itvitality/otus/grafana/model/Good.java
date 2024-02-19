package ru.itvitality.otus.grafana.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Good {
    private String id;

    private Long cost;

    private String name;
}
