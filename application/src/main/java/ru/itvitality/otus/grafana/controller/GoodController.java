package ru.itvitality.otus.grafana.controller;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.itvitality.otus.grafana.model.dto.Good;
import ru.itvitality.otus.grafana.service.GoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
// TODO any request to kafka
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/api/v1/goods")
    @Timed(histogram = true, value = "good_controller_list")
    public List<Good> list() {
        sleep();
        return goodService.list();
    }

    @GetMapping("/api/v1/goods/{goodId}")
    @Timed(histogram = true, value = "good_controller_find")
    public Good find( @PathVariable("goodId") String goodId ) {
        return goodService.find( goodId );
    }

    @PostMapping("/api/v1/goods")
    @Timed(histogram = true, value = "good_controller_save")
    public Good save( @RequestBody Good good ) {
        return goodService.save( good );
    }

    private void sleep() {
        var time = Math.round( Math.random() * 5500l );
        // Для периодических подтормаживаний, чтобы была timeOutException
        try {
            Thread.sleep( time );
        } catch ( InterruptedException e ) {
            e.printStackTrace();
        }
    }
}
