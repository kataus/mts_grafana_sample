package ru.itvitality.otus.grafana.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itvitality.otus.grafana.model.dto.Good;
import ru.itvitality.otus.grafana.model.entity.GoodEntity;
import ru.itvitality.otus.grafana.repository.GoodRepository;
import ru.itvitality.otus.grafana.service.GoodService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class GoodServiceImpl implements GoodService {
    private final GoodRepository repository;

    @Override
    public List<Good> list() {
        return repository.findAll().stream()
                .map( this::mapToDto )
                .toList();
    }

    @Override
    @Transactional
    public Good save( Good good ) {
        return Optional.ofNullable( good.getId() )
                .map( id -> repository.findById( id ).get() )
                .or( () -> Optional.of( GoodEntity.builder()
                        .id( UUID.randomUUID().toString() )
                        .name( good.getName() )
                        .cost( good.getCost() )
                        .build() ) )
                .stream()
                .peek( repository::save )
                .map( this::mapToDto )
                .findFirst().get();
    }

    @Override
    public Good find( String goodId ) {
        return repository.findById( goodId )
                .map( this::mapToDto )
                .get();
    }

    private Good mapToDto( GoodEntity goodEntity ) {
        return Good.builder()
                .id( goodEntity.getId() )
                .cost( goodEntity.getCost() )
                .name( goodEntity.getName() )
                .build();
    }
}
