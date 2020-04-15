package tests;

import app.AppController;
import cache.MemoryCache;

import entities.*;
import form.Form;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MockTests extends Exception {

    @InjectMocks
    AppController appController = new AppController();
    @Mock
    MemoryCache<Entitie> mockedMemoryCache = new MemoryCache<>();
    @Mock
    Entitie entitie = new Entitie();
    @Mock
    Data data;
    @Mock
    RestTemplate rt;
    @Mock
    Form form;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
//        mockedMemoryCache.put("Los Angeles", entitie);
        mockedMemoryCache.put("Aveiro", entitie);
    }

    @Test
    void testPutCache(){
        System.out.println("Executing testPutCache()");
        Mockito.doNothing().when(mockedMemoryCache).put("Los Angeles", entitie);
        mockedMemoryCache.put("Los Angeles", entitie);
        verify(mockedMemoryCache).put("Los Angeles", entitie);
        assertEquals(entitie, mockedMemoryCache.get("Los Angeles"));
        assertNotEquals(entitie, mockedMemoryCache.get("Lisboa"));
    }

    @Test
    void testDelCache() {
        System.out.println("Executing testDelCache()");
        Mockito.doCallRealMethod().when(mockedMemoryCache).delete("Aveiro", entitie);
        mockedMemoryCache.delete("Aveiro", entitie);
        verify(mockedMemoryCache).delete("Aveiro", entitie);
    }

    @Test
    public void whenPutCalledVerified() {
        System.out.println("Mock tests");
        MemoryCache mockedCache = Mockito.mock(MemoryCache.class);
        Entitie et = new Entitie();
        mockedCache.put("Mock", et);
        verify(mockedCache, times(1)).put("Mock", et);
    }



}
