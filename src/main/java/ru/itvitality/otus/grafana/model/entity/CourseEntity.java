package ru.itvitality.otus.grafana.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column(name = "year_start")
    private Integer yearStart;

    @OneToMany(mappedBy = "course")
    private Set<StudentEntity> students;
}
