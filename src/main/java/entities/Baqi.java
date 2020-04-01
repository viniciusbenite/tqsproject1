package entities;

public class Baqi {

    private String display_name;
    private String BreezoMeter;
    private int aqi;
    private String aqi_display;
    private String color;
    private String category;
    private String dominant_pollutant;

    public Baqi() {
    }

    public String getDisplay_name() {
        return display_name;
    }

    public void setDisplay_name(String display_name) {
        this.display_name = display_name;
    }

    public String getBreezoMeter() {
        return BreezoMeter;
    }

    public void setBreezoMeter(String breezoMeter) {
        BreezoMeter = breezoMeter;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public String getAqi_display() {
        return aqi_display;
    }

    public void setAqi_display(String aqi_display) {
        this.aqi_display = aqi_display;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDominant_pollutant() {
        return dominant_pollutant;
    }

    public void setDominant_pollutant(String dominant_pollutant) {
        this.dominant_pollutant = dominant_pollutant;
    }

    @Override
    public String toString() {
        return "Baqi{" +
                "display_name='" + display_name + '\'' +
                ", BreezoMeter='" + BreezoMeter + '\'' +
                ", aqi=" + aqi +
                ", aqi_display='" + aqi_display + '\'' +
                ", color='" + color + '\'' +
                ", category='" + category + '\'' +
                ", dominant_pollutant='" + dominant_pollutant + '\'' +
                '}';
    }
}
