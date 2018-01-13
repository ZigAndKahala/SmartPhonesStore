package sample;

public class Incoming {
    private String name;
    private String date;
    private String quntity;
    private String price;

    public Incoming(String name, String date, String quntity, String price) {
        this.name = name;
        this.date = date;
        this.quntity = quntity;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
