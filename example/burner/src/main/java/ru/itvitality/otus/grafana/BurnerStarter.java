package ru.itvitality.otus.grafana;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itvitality.otus.grafana.service.BurnerService;

@SpringBootApplication
public class BurnerStarter {
    public static void main( String[] args ) {
        var context = SpringApplication.run( BurnerStarter.class, args );

        var burner = context.getBean( BurnerService.class );
        burner.burn();
    }
}
