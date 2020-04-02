package entities;

public class Current {

    private Pollution pollution;
    private Weather weather;

    public Current() {  }

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

    @Override
    public String toString() {
        return "Current{" +
                "pollution=" + pollution +
                ", weather=" + weather +
                '}';
    }
}
