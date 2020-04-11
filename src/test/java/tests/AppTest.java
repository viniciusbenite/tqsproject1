package tests;

import app.AppController;
import app.ConsumingRestApplication;
import cache.MemoryCache;
import entities.Entitie;
import form.Form;

import org.junit.jupiter.api.Test;
import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(classes = ConsumingRestApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AppTest {

    @Autowired
    private AppController controller;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void greetingShouldReturnDefaultMessage() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("Please, enter the location to get air quality data");
    }

    @Test
    public void contextLoads() throws Exception{
        assertThat(controller).isNotNull();
    }

    @Test
    public void memChacheInitiationNull() throws Exception {
        // given
        MemoryCache<Entitie> memCache = new MemoryCache<>();
        // when
        Entitie getMemCacheObj = memCache.get("Aveiro");
        // then
        assertNull("Cache must be null on creation, without any API calls", getMemCacheObj);
    }

    @Test
    public void apiCallTest() throws Exception {
        // given
        Form form = new Form();
        form.setCity("Aveiro");
        form.setCountry("Portugal");
        form.setState("Aveiro");
        // when
        Entitie et = restTemplate.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
        // then
        assertEquals("API response", "<Entitie{status=success, data=Data{, city='Aveiro', state='Aveiro', country='Portugal', " +
                        "location=Location{type='Point', coordinates=[-8.646666666666667, 40.635555555555555]}, " +
                        "current=Current{pollution=Pollution{timestamp='2020-04-11T14:00:00.000Z', aqius=0.0, mainus='n2', " +
                        "aqicn=2.0, maincn='n2'}, weather=Weather{timestamp='2020-04-11T15:00:00.000Z', " +
                        "tp=17.0, pr=1019.0, hu=77.0, ws=4.6, wd=320.0, ic='03d'}}}}> ",
                "<Entitie{status=success, data=Data{, city='Aveiro', state='Aveiro', " +
                        "country='Portugal', location=Location{type='Point', coordinates=[-8.646666666666667, 40.635555555555555]}, " +
                        "current=Current{pollution=Pollution{timestamp='2020-04-11T14:00:00.000Z', aqius=0.0, mainus='n2', aqicn=2.0, maincn='n2'}, " +
                        "weather=Weather{timestamp='2020-04-11T15:00:00.000Z', tp=17.0, pr=1019.0, hu=77.0, ws=4.6, wd=320.0, ic='03d'}}}}> ");

    }

    @Test
    public void memCachePutTest() throws Exception {
        // given
        MemoryCache<Entitie> memCache = new MemoryCache<>();
        Form form = new Form();
        form.setCity("Aveiro");
        form.setCountry("Portugal");
        form.setState("Aveiro");
        Entitie et = restTemplate.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
        memCache.put(form.getCity(), et);
        // when
        Entitie getMemCacheObj = memCache.get("Aveiro");
        // then
        assertEquals("Entitie object expected: ", et, getMemCacheObj);
    }

    @Test
    public void memCacheGetTest() throws Exception {
        // given
        MemoryCache<Entitie> memCache = new MemoryCache<>();
        Form form = new Form();
        form.setCity("Aveiro");
        form.setCountry("Portugal");
        form.setState("Aveiro");
        Entitie et = restTemplate.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
        memCache.put(form.getCity(), et);
        // when
        Entitie response = memCache.get("Aveiro");
        // then
        assertEquals("Results from API call and cache must be the same", response, et);
    }

    @Test
    public void getExpireTimeTest() throws Exception {
        // given
        MemoryCache<Entitie> memCache = new MemoryCache<>();
        Form form = new Form();
        form.setCity("Aveiro");
        form.setCountry("Portugal");
        form.setState("Aveiro");
        Entitie et = restTemplate.getForObject("http://api.airvisual.com/v2/city?city="+ form.getCity() + "&state=" + form.getState() + "&country="+ form.getCountry() +"&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class);
        memCache.put(form.getCity(), et);
        // when
        long expireTime = memCache.getExpire();
        // then
        assertEquals("100 is the default expire time for cache", 100, expireTime);
    }
}