package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FormInputPage {

    protected static WebDriver driver;

    public FormInputPage(WebDriver driver) {
        this.driver = driver;
    }

    public ResultPage validSearch(String country, String state, String city) {
        driver.findElement(By.id("country")).clear();
        driver.findElement(By.id("country")).sendKeys(country);
        driver.findElement(By.id("state")).clear();
        driver.findElement(By.id("state")).sendKeys(state);
        driver.findElement(By.id("city")).clear();
        driver.findElement(By.id("city")).sendKeys(city);
        driver.findElement(By.id("submit")).click();

        return new ResultPage(driver);
    }
}
