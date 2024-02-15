package ru.itvitality.otus.grafana.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itvitality.otus.grafana.model.entity.GoodEntity;

public interface GoodRepository extends JpaRepository<GoodEntity, String> {

}
