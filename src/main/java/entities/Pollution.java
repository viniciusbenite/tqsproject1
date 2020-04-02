package entities;

public class Pollution {

    private String ts;
    private double aqius;
    private String mainus;
    private double aqicn;
    private String maincn;

    public Pollution() {
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
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

    @Override
    public String toString() {
        return "Pollution{" +
                "timestamp='" + ts + '\'' +
                ", aqius=" + aqius +
                ", mainus='" + mainus + '\'' +
                ", aqicn=" + aqicn +
                ", maincn='" + maincn + '\'' +
                '}';
    }
}
