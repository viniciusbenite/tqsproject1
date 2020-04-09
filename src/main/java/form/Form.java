package form;

import entities.Entitie;

public class Form {

    private String country;
    private String state;
    private String city;
    private Entitie entitie;

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

    public Entitie getEntitie() {
        return entitie;
    }

    public void setEntitie(Entitie entitie) {
        this.entitie = entitie;
    }
}