package sample;

public class EmployeeTable {
    private String name;
    private String email;
    private String number;
    private String type;
    private String isWorking;

    public EmployeeTable(String name, String email, String number, String type, String isWorking) {
        this.name = name;
        this.email = email;
        this.number = number;
        this.type = type;
        this.isWorking = isWorking;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(String isWorking) {
        this.isWorking = isWorking;
    }
}
