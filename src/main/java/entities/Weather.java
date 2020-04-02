package entities;

public class Weather {

    private String ts;
    private double tp;
    private double pr;
    private double hu;
    private double ws;
    private double wd;
    private String ic;

    public Weather() {
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getPr() {
        return pr;
    }

    public void setPr(double pr) {
        this.pr = pr;
    }

    public double getHu() {
        return hu;
    }

    public void setHu(double hu) {
        this.hu = hu;
    }

    public double getWs() {
        return ws;
    }

    public void setWs(double ws) {
        this.ws = ws;
    }

    public double getWd() {
        return wd;
    }

    public void setWd(double wd) {
        this.wd = wd;
    }

    public String getIc() {
        return ic;
    }

    public void setIc(String ic) {
        this.ic = ic;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "timestamp='" + ts + '\'' +
                ", tp=" + tp +
                ", pr=" + pr +
                ", hu=" + hu +
                ", ws=" + ws +
                ", wd=" + wd +
                ", ic='" + ic + '\'' +
                '}';
    }
}
