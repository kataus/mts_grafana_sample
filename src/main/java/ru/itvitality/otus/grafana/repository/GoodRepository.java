package ru.itvitality.otus.grafana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itvitality.otus.grafana.model.entity.GoodEntity;

import java.util.UUID;

public interface GoodRepository extends JpaRepository<GoodEntity, String> {

}
