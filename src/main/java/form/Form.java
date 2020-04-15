package form;

import entities.*;

public class Form {

    private String country;
    private String state;
    private String city;
    private Entitie entitie;
    private Data data;
    private Current current;
    private Weather weather;
    private Pollution pollution;
    private double temperature;
    private double pressure;
    private double humidity;
    private double aqius;
    private String mainus;
    private double aqicn;
    private String maincn;

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Entitie getEntitie() { return entitie; }

    public void setEntitie(Entitie entitie) { this.entitie = entitie; }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    public Weather getWeather() {
        return weather;
    }

    public void setWeather(Weather weather) {
        this.weather = weather;
    }

    public Pollution getPollution() {
        return pollution;
    }

    public void setPollution(Pollution pollution) {
        this.pollution = pollution;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getPressure() {
        return pressure;
    }

    public void setPressure(double pressure) {
        this.pressure = pressure;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public double getAqius() {
        return aqius;
    }

    public void setAqius(double aqius) {
        this.aqius = aqius;
    }

    public String getMainus() {
        return mainus;
    }

    public void setMainus(String mainus) {
        this.mainus = mainus;
    }

    public double getAqicn() {
        return aqicn;
    }

    public void setAqicn(double aqicn) {
        this.aqicn = aqicn;
    }

    public String getMaincn() {
        return maincn;
    }

    public void setMaincn(String maincn) {
        this.maincn = maincn;
    }
}