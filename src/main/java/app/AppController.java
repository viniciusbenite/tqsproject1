package app;

import entities.Entitie;
import entities.Weather;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
    private String city;
    private String country;
    private String state;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @RequestMapping("/")
    public String index(RestTemplate rt) {
        Entitie airQuality = rt.getForObject("http://api.airvisual.com/v2/city?city=Los Angeles&state=California&country=USA&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
        assert airQuality != null;
        log.info(airQuality.toString());
        log.info(airQuality.getData().toString());
        log.info(airQuality.getData().getCurrent().getWeather().toString());
        return airQuality.toString();
    }
}