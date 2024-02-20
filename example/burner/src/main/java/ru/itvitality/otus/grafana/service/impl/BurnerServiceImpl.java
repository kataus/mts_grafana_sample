package ru.itvitality.otus.grafana.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.itvitality.otus.grafana.kafka.ProcessingProducer;
import ru.itvitality.otus.grafana.model.Good;
import ru.itvitality.otus.grafana.service.BurnerService;
import ru.itvitality.otus.grafana.service.GoodService;

import java.util.Date;

@RequiredArgsConstructor
@Slf4j
@Service
public class BurnerServiceImpl implements BurnerService {
    private final GoodService goodService;
    private final ProcessingProducer producer;

    @Override
    public void burn() {
        // Запустить поток что будет забирать список заказов
        while ( true ) {
            var goods = goodService.findAll();
            log.info( "{} goods loaded", goods.size() );
            goods.forEach( producer::process );
            var random = Math.round( Math.random() * 1000l );
            if ( random > 970 ) {
                // Иногда добавлять новый good
                var name = "autogen_" + new Date().getTime();
                var cost = Math.round( Math.random() * 1000 ) + 100;
                var good = Good.builder()
                        .name( name )
                        .cost( cost )
                        .build();
                goodService.save( good );
            }
        }
    }

    @Override
    public void process( Good good ) {
        var loadedGood = goodService.find( good.getId() );

        var random = Math.round( Math.random() * 1000l );
        if ( random > 900L ) {
            loadedGood.setCost( loadedGood.getCost() + 7 );
            goodService.save( loadedGood );
        }

    }
}
