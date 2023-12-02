package io.github.ndimovt.hospitalv2;
public class Nurse extends Human{
    private String username = "nurse1";
    private String password = "password1";
    public Nurse() {
        super();
    }
    public Nurse(String forname, String fathername, String surname, String address, long phoneNumber, String dateIn) {
        super(forname, fathername, surname, address, phoneNumber, dateIn);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
