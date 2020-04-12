package tests;

import Selenium.FormInputPage;
import Selenium.ResultPage;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SeleniumTest {

    WebDriver driver = new FirefoxDriver();

    @Test
    public void testFormInput() {
        driver.get("http://localhost:8080/");
        FormInputPage formPage = new FormInputPage(driver);
        ResultPage resultPage = formPage.validSearch("Brazil", "Sao Paulo", "Pederneiras");
        assertThat(resultPage.getMessageText(), is("Air Quality Data"));
        assertThat(resultPage.getCityText(), is("City: Pederneiras"));
    }

    @Test
    public void testGoBack() {
        driver.get("http://localhost:8080/");
        FormInputPage formPage = new FormInputPage(driver);
        ResultPage resultPage = formPage.validSearch("Brazil", "Sao Paulo", "Pederneiras");
        resultPage.goBack();
        ResultPage resultPage2 = formPage.validSearch("Portugal", "Aveiro", "Aveiro");
        assertThat(resultPage2.getCityText(), is("City: Aveiro"));
    }

    @Test
    public void failTest() {
        driver.get("http://localhost:8080/");
        FormInputPage formPage = new FormInputPage(driver);
        ResultPage resultPage = formPage.validSearch("Foo", "Bar", "Bla");
        assertThat(resultPage.getMessageText(), is("404 Error"));
    }

}
