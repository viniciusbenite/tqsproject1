package app;

import entities.Entitie;
import form.Form;
import cache.MemoryCache;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
@Api(value = "TQS Project Rest API")
public class AppController {

    private static final Logger log = LoggerFactory.getLogger(ConsumingRestApplication.class);

    MemoryCache<Entitie> memCache = new MemoryCache<>();

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @ApiOperation(value = "Welcome page, form")
    @GetMapping("/")
    public String form(Model model) {
        model.addAttribute("form", new Form());
        return "form";
    }

    @ApiOperation(value = "Get weather and pollution data based on country, state and city")
    @PostMapping("/")
    public String getAirQuality(@ModelAttribute Form form, RestTemplate rt) {
        try {
            Entitie e = memCache.get(form.getCity(), Entitie.class);
            if (e != null) {
                form.setEntitie(e);
                form.setData(e.getData());
                form.setTemperature(e.getData().getCurrent().getWeather().getTp());
                form.setPressure(e.getData().getCurrent().getWeather().getPr());
                form.setHumidity(e.getData().getCurrent().getWeather().getHu());
                form.setAqius(e.getData().getCurrent().getPollution().getAqius());
                form.setMainus(e.getData().getCurrent().getPollution().getMainus());
                form.setAqicn(e.getData().getCurrent().getPollution().getAqicn());
                form.setMaincn(e.getData().getCurrent().getPollution().getMaincn());
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
            form.setData(airQuality.getData());
            form.setTemperature(airQuality.getData().getCurrent().getWeather().getTp());
            form.setPressure(airQuality.getData().getCurrent().getWeather().getPr());
            form.setHumidity(airQuality.getData().getCurrent().getWeather().getHu());
            form.setAqius(airQuality.getData().getCurrent().getPollution().getAqius());
            form.setMainus(airQuality.getData().getCurrent().getPollution().getMainus());
            form.setAqicn(airQuality.getData().getCurrent().getPollution().getAqicn());
            form.setMaincn(airQuality.getData().getCurrent().getPollution().getMaincn());
            memCache.put(airQuality.getData().getCity(), airQuality);
            log.info("DATA FETCHED FROM API");
            return "result";
        }
        catch (final HttpClientErrorException e) {
            log.error(String.valueOf(e.getStatusCode()));
            log.error((e.getResponseBodyAsString()));
        }
        return "fail";
    }

    @ApiOperation(value = "Delete data from the memory cache, based on city")
    @DeleteMapping("/del/{city}/")
    public String deleteDataFromMemCache(@PathVariable("city") String city,
                                         @ModelAttribute Form form) {
        try {
            Entitie et = memCache.get(form.getCity(), Entitie.class);
            if (et != null) {
                memCache.delete(city, et);
                log.info("City " + et.getData().getCity() + " deleted from cache");
                return "delete_confirmation";
            }
        } catch (final HttpClientErrorException e) {
            log.error(String.valueOf(e.getStatusCode()));
            log.error((e.getResponseBodyAsString()));
        }
        return "fail";
    }
}