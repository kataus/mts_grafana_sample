package ru.itvitality.otus.grafana.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import ru.itvitality.otus.grafana.model.Good;
import ru.itvitality.otus.grafana.service.GoodService;

import java.time.Duration;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoodServiceImpl implements GoodService {
    RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout( Duration.ofSeconds( 10L ) )
            .build();

    private String endPoint = "http://localhost:8095";

    @Override
    public List<Good> findAll() {
        var request = new RequestEntity( HttpMethod.GET,
                UriComponentsBuilder.fromHttpUrl( endPoint )
                        .path( "/api/v1/goods" )
                        .build().toUri() );

        var responseType = new ParameterizedTypeReference<List<Good>>() {
        };
        var response = restTemplate.exchange( request, responseType );
        return response.getBody();
    }

    @Override
    public Good save( Good good ) {
        var request = new RequestEntity( good, HttpMethod.POST,
                UriComponentsBuilder.fromHttpUrl( endPoint )
                        .path( "/api/v1/goods" )
                        .build().toUri() );

        var responseType = new ParameterizedTypeReference<Good>() {
        };
        var response = restTemplate.exchange( request, responseType );
        return response.getBody();
    }

    @Override
    public Good find( String id ) {
        var request = new RequestEntity( HttpMethod.GET,
                UriComponentsBuilder.fromHttpUrl( endPoint )
                        .path( "/api/v1/goods/{id}" )
                        .buildAndExpand( id )
                        .toUri() );

        var responseType = new ParameterizedTypeReference<Good>() {
        };
        var response = restTemplate.exchange( request, responseType );
        return response.getBody();
    }
}
