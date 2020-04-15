package tests;

import app.AppController;
import entities.Entitie;
import form.Form;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@RunWith(MockitoJUnitRunner.class)
public class RestTemplateTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private final AppController appController = new AppController();

    @Test
    public void givenMockingIsDoneByMockito_whenGetIsCalled_shouldReturnMockedObject() {
        RestTemplate rt = new RestTemplate();
        Form form = new Form();
        form.setCity("Aveiro");
        form.setCountry("Portugal");
        form.setState("Aveiro");
        String response = "result";
        Mockito
                .lenient().when(restTemplate.getForEntity(
                        "api.airvisual.com/v2/city?city=Aveiro&state=Aveiro&country=Portugal&key=2fa6f39e-db55-44e5-8efa-cf202e452a4b", Entitie.class))
          .thenReturn(new ResponseEntity(response, HttpStatus.OK));


        String ActualResponse = appController.getAirQuality(form, rt);
        Assert.assertEquals("Rest template service test", response, ActualResponse);
    }
}
