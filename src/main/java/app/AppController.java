package app;

import entities.Entitie;
import form.Form;
import cache.MemoryCache;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    private final List<String> data = new ArrayList<>();
    private final Map<String, List<String>> myMap = new HashMap<>();

    MemoryCache<Entitie> memCache = new MemoryCache<>();

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
            Entitie e = memCache.get(form.getCity(), Entitie.class);
            if (e != null) {
                form.setEntitie(e);
                log.info("DATA FETCHED FROM CACHE");
                log.info("Expire time: " + memCache.getExpire());
                return "result";
            }
        } catch (final HttpClientErrorException.NotFound e) {
            log.error(String.valueOf(e.getStatusCode()));
            log.error((e.getResponseBodyAsString()));
        }
        try {
            Entitie airQuality = rt.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
            assert airQuality != null;
            log.info(airQuality.getStatus());
            form.setEntitie(airQuality);
            memCache.put(airQuality.getData().getCity(), airQuality);
            saveData(airQuality);
            log.info("DATA FETCHED FROM API");
            return "result";
        }
        catch (final HttpClientErrorException e) {
            log.error(String.valueOf(e.getStatusCode()));
            log.error((e.getResponseBodyAsString()));
        }
        return "fail";
    }

    public void saveData(Entitie et) {
        data.add(et.getData().getCurrent().getPollution().toString());
        data.add(et.getData().getCurrent().getWeather().toString());
        myMap.put(et.getData().getCity(), data);
    }
}