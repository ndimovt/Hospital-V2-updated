package io.github.ndimovt.hospitalv2;

public class Doctor extends Human{
    private String username = "doctor1";
    private String password = "password2";

    public Doctor() {
    }
    public Doctor(String forname, String fathername, String surname, String address, long phoneNumber, String dateIn) {
        super(forname, fathername, surname, address, phoneNumber, dateIn);
    }

    public String getUsername() {
        return username;
    }
    public String getPassword() {
        return password;
    }
}
