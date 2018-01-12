package sample;

public class accessoryPurchase {
    private String acsName;
    private String acsBuyDate;

    public accessoryPurchase(String acsName, String acsBuyDate) {
        this.acsName = acsName;
        this.acsBuyDate = acsBuyDate;
    }

    public String getAcsName() {
        return acsName;
    }

    public void setAcsName(String acsName) {
        this.acsName = acsName;
    }

    public String getAcsBuyDate() {
        return acsBuyDate;
    }

    public void setAcsBuyDate(String acsBuyDate) {
        this.acsBuyDate = acsBuyDate;
    }
}
