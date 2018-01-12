package sample;

public class phonePurchase {
    private String phone;
    private String phoneBuyDate;

    public phonePurchase(String phone, String phoneBuyDate) {
        this.phone = phone;
        this.phoneBuyDate = phoneBuyDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhoneBuyDate() {
        return phoneBuyDate;
    }

    public void setPhoneBuyDate(String phoneBuyDate) {
        this.phoneBuyDate = phoneBuyDate;
    }
}
