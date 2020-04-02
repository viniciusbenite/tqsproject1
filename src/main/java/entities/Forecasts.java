package entities;

public class Forecasts {

    private String ts;
    private int aqius;
    private int aqicn;
    private double tp;
    private double tpMin;
    private double pr;
    private double hu;
    private double ws;
    private double wd;
    private String ic;

    public Forecasts() {
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public int getAqius() {
        return aqius;
    }

    public void setAqius(int aqius) {
        this.aqius = aqius;
    }

    public int getAqicn() {
        return aqicn;
    }

    public void setAqicn(int aqicn) {
        this.aqicn = aqicn;
    }

    public double getTp() {
        return tp;
    }

    public void setTp(double tp) {
        this.tp = tp;
    }

    public double getTpMin() {
        return tpMin;
    }

    public void setTpMin(double tpMin) {
        this.tpMin = tpMin;
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
        return "Forecasts{" +
                "timestamp='" + ts + '\'' +
                ", aqius=" + aqius +
                ", aqicn=" + aqicn +
                ", tp=" + tp +
                ", tpMin=" + tpMin +
                ", pr=" + pr +
                ", hu=" + hu +
                ", ws=" + ws +
                ", wd=" + wd +
                ", ic='" + ic + '\'' +
                '}';
    }
}
