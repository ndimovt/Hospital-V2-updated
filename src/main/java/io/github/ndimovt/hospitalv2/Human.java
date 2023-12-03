package io.github.ndimovt.hospitalv2;

public class Human {
    private String forename;
    private String fatherName;
    private String surname;
    private long EGN;
    private String address;
    private long phoneNumber;
    private String dateIn;
    private String illness;
    private String treatment;

    public Human() {
    }

    public Human(String forename, String fatherName, String surname, long EGN, String address, String illness, String treatment, String dateIn) {
        super();
        this.forename = forename;
        this.fatherName = fatherName;
        this.surname = surname;
        this.EGN = EGN;
        this.address = address;
        this.illness = illness;
        this.treatment = treatment;
        this.dateIn = dateIn;
    }

    public Human(String forename, String fatherName, String surname, String address, long phoneNumber, String dateIn) {
        this.forename = forename;
        this.fatherName = fatherName;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.dateIn = dateIn;
    }

    @Override
    public String toString() {
        return forename +"/ "+ fatherName +"/ "+ surname +"/ "+ address +"/ "+ phoneNumber +"/ "+dateIn;
    }
    public String patientToString(){
        return forename +"/ "+ fatherName +"/ "+ surname +"/ "+ address +"/ "+EGN+"/ "+illness+"/ "+treatment+"/ "+dateIn;
    }

}
