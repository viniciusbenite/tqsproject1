package app;

import entities.Entitie;
import form.Form;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);
    private String city;
    private String country;
    private String state;

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @PostMapping("/")
    public String greetingSubmit(@ModelAttribute Form form, RestTemplate rt) {
        try {
            Entitie airQuality = rt.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
            assert airQuality != null;
            log.error(airQuality.getStatus());
            form.setEntitie(airQuality);
            log.info(airQuality.toString());
            log.info(airQuality.getData().toString());
            log.info(airQuality.getData().getCurrent().getWeather().toString());
            return "result";
        }
        catch (final HttpClientErrorException e) {
            log.error(String.valueOf(e.getStatusCode()));
            log.error((e.getResponseBodyAsString()));
        }
        return "fail";
    }
}