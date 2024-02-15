package ru.itvitality.otus.grafana.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itvitality.otus.grafana.model.dto.Good;
import ru.itvitality.otus.grafana.service.GoodService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping("/api/v1/goods")
    @Timed(histogram = true, value = "good_controller_list")
    public List<Good> list(){
        return goodService.list();
    }

    @GetMapping("/api/v1/goods/{goodId}")
    @Timed(histogram = true, value = "good_controller_find")
    public Good find( @PathVariable("goodId") String goodId ){
        return goodService.find(goodId);
    }

    @PostMapping("/api/v1/goods")
    @Timed(histogram = true, value = "good_controller_save")
    public Good save( Good good ){
        return goodService.save( good );
    }
}
