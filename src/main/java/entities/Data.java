package entities;

public class Data {

    private String datetime;
    private String data_available;
    private Indexes indexes;

    public Data() {
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public String getData_available() {
        return data_available;
    }

    public void setData_available(String data_available) {
        this.data_available = data_available;
    }

    public Indexes getIndexes() {
        return indexes;
    }

    public void setIndexes(Indexes indexes) {
        this.indexes = indexes;
    }

    @Override
    public String toString() {
        return "Data{" +
                "datetime='" + datetime + '\'' +
                ", data_available='" + data_available + '\'' +
                ", indexes='" + indexes + '\'' +
                '}';
    }
}
