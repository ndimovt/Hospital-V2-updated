package io.github.ndimovt.hospitalv2;
public class Patient extends Human{
    public Patient(String forename, String fatherName, String surname, long EGN, String address, String illness, String treatment, String dateIn) {
        super(forename, fatherName, surname, EGN, address, illness, treatment, dateIn);
    }
}
