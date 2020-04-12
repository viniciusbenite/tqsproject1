package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ResultPage {

    protected static WebDriver driver;

    public ResultPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMessageText() {
        return driver.findElement(By.tagName("h1")).getText(); // Air Quality Data
    }

    public String getCityText() {
        return driver.findElement(By.id("city")).getText();
    }

    public void goBack() {
        driver.findElement(By.tagName("a")).click();
    }
}
