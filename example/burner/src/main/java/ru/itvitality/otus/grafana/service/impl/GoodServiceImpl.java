package ru.itvitality.otus.grafana.service.impl;

import io.micrometer.core.annotation.Timed;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
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
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class GoodServiceImpl implements GoodService {
    RestTemplate restTemplate = new RestTemplateBuilder()
            .setConnectTimeout( Duration.ofSeconds( 5L ) )
            .setReadTimeout( Duration.ofSeconds( 5L ) )
            .build();

    @Value("${burner.clients}")
    private List<String> endPoints;
    private Integer epOrded = 0;

    @Override
    @Timed(value = "good_service_list", histogram = true)
    public List<Good> findAll() {
        log.info( "Try to get all goods" );
        String endPoint = getEndPoint();

        var request = new RequestEntity( HttpMethod.GET,
                UriComponentsBuilder.fromHttpUrl( endPoint )
                        .path( "/api/v1/goods" )
                        .build().toUri() );

        var responseType = new ParameterizedTypeReference<List<Good>>() {
        };
        try {
            var response = restTemplate.exchange( request, responseType );
            return response.getBody();
        } catch ( RuntimeException e ) {
            log.error( "can't load data", e );
            return new ArrayList<>();
        }
    }

    @Override
    @Timed(value = "good_service_save", histogram = true)
    public Good save( Good good ) {
        var request = new RequestEntity( good, HttpMethod.POST,
                UriComponentsBuilder.fromHttpUrl( getEndPoint() )
                        .path( "/api/v1/goods" )
                        .build().toUri() );

        var responseType = new ParameterizedTypeReference<Good>() {
        };

        try {
            var response = restTemplate.exchange( request, responseType );
            return response.getBody();
        } catch ( RuntimeException e ) {
            log.error( "can't load data", e );
            return null;
        }
    }

    @Override
    @Timed(value = "good_service_find", histogram = true)
    public Good find( String id ) {
        var request = new RequestEntity( HttpMethod.GET,
                UriComponentsBuilder.fromHttpUrl( getEndPoint() )
                        .path( "/api/v1/goods/{id}" )
                        .buildAndExpand( id )
                        .toUri() );

        var responseType = new ParameterizedTypeReference<Good>() {
        };
        var response = restTemplate.exchange( request, responseType );
        return response.getBody();
    }

    private String getEndPoint() {
        // Фигня полнейшая так не делать, только для демо проекта
        var endPoint = endPoints.get( epOrded );
        epOrded++;
        if ( epOrded >= endPoints.size() ) {
            epOrded = 0;
        }
        return endPoint;
    }
}
