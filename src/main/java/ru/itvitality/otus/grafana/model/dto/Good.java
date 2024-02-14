package ru.itvitality.otus.grafana.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder(toBuilder = true)
@AllArgsConstructor
public class Good {
    private String id;

    private Long cost;

    private String name;
}
