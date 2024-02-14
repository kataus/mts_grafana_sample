package ru.itvitality.otus.grafana.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "goods")
public class GoodEntity {

    @Id
    private String id;

    @Column
    private Long cost;

    @Column
    private String name;
}
