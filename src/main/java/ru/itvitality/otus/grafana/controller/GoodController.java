package ru.itvitality.otus.grafana.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itvitality.otus.grafana.model.dto.Good;
import ru.itvitality.otus.grafana.service.GoodService;

import java.util.List;
import java.util.UUID;

@RestController("/api/v1/goods")
@RequiredArgsConstructor
public class GoodController {
    private final GoodService goodService;

    @GetMapping()
    public List<Good> list(){
        return goodService.list();
    }

    @GetMapping("/{goodId}")
    public Good find( @PathVariable("goodId") String goodId ){
        return goodService.find(goodId);
    }

    @PostMapping
    public Good save( Good good ){
        return goodService.save( good );
    }
}
