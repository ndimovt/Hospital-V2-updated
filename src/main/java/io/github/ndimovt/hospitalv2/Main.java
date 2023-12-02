package io.github.ndimovt.hospitalv2;

import io.github.ndimovt.hospitalv2.sqlfunctionalities.AddInformationToDB;
import io.github.ndimovt.hospitalv2.sqlfunctionalities.GetInformationFromDB;
import io.github.ndimovt.hospitalv2.sqlfunctionalities.RemoveStaffFromDB;

import java.util.Scanner ;
public class Main {
    public static void main(String[] args) {
        GetInformationFromDB getInformationFromDB = GetInformationFromDB.getInstance();
        RemoveStaffFromDB removeStaffFromDB = RemoveStaffFromDB.getInstance();
        AddInformationToDB addInfoToDB = AddInformationToDB.getInstance();
        Scanner inn = new Scanner(System.in);
        Doctor doctor = new Doctor();
        Nurse nurse = new Nurse();
        DepartmentHead dh = new DepartmentHead();
        System.out.println("Welcome to City hospital");
        boolean isTrue = true;
        while (isTrue) {
            System.out.println("Choose option from the menu: 1) Enter in the system 2)Exit the system");
            int choice = inn.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter username");
                    inn.nextLine();
                    String username = inn.nextLine();
                    System.out.println("Enter password");
                    String password = inn.nextLine();
                    if ((username.equals(doctor.getUsername())) && (password.equals(doctor.getPassword()))) {
                        boolean thisMenu = true;
                        System.out.println("You enter the system as doctor");
                        while (thisMenu) {
                            System.out.println("1)Check patients with no treatment and illness 2) Set illness and treatment 3)Release patient 4)Back to main menu");
                            int doctorChoice = inn.nextInt();
                            switch (doctorChoice) {
                                case 1:
                                        getInformationFromDB.checkPatientsInfo();
                                    break;
                                case 2:
                                    String noTreatmentPatientEGN;
                                    System.out.println("Enter patient's EGN (must be 10 symbols long)");
                                    do {
                                        noTreatmentPatientEGN = inn.nextLine();
                                    } while (noTreatmentPatientEGN.length() != 10);
                                    long egn = Long.parseLong(noTreatmentPatientEGN);
                                    System.out.println("Enter patient's illness");
                                    String illness = inn.nextLine();
                                    System.out.println("Enter patient's treatment");
                                    String treatment = inn.nextLine();
                                        addInfoToDB.updatePatient(illness, treatment, egn);
                                    break;
                                case 3:
                                    String releasePatientEGN;
                                    System.out.println("Enter patient's EGN1");
                                    do {
                                        releasePatientEGN = inn.nextLine();
                                    } while (releasePatientEGN.length() != 10);
                                    egn = Long.parseLong(releasePatientEGN);
                                        removeStaffFromDB.releasePatient(egn);
                                    break;
                                case 4:
                                    thisMenu = false;
                            }
                        }
                    } else if ((username.equals(nurse.getUsername())) && (password.equals(nurse.getPassword()))) {
                        System.out.println("Enter forname");
                        String forname = inn.nextLine();
                        System.out.println("Enter fathername");
                        String fathername = inn.nextLine();
                        System.out.println("Enter surname");
                        String surname = inn.nextLine();
                        String EGN;
                        do {
                            System.out.println("Enter EGN (must be 10 symbols long)");
                            EGN = inn.nextLine();
                        } while (EGN.length() != 10);
                        System.out.println("Enter address");
                        String address = inn.nextLine();
                        long egn = Long.parseLong(EGN);
                            addInfoToDB.addPatientToDB(forname, fathername, surname, egn, address);
                    } else if ((username.equals(dh.getUsername())) && (password.equals(dh.getPassword()))) {
                        boolean depHead = true;
                        System.out.println("You enter the system as Head of the Department");
                        while (depHead) {
                            System.out.println("1) Add nurse to the system 2)Add doctor to system 3)Release nurse 4)Release doctor " +
                                    "5) Get information for Doctors 6)Get information for Nurses 7)Get information for Patients 8)Back to main menu");
                            int departmentHead = inn.nextInt();
                            switch (departmentHead) {
                                case 1:
                                    String nurseEGN;
                                    String nursePersonalPhoneNumber;
                                    System.out.println("Enter forname");
                                    inn.nextLine();
                                    String nurseForname = inn.nextLine();
                                    System.out.println("Enter fathername");
                                    String nurseFathername = inn.nextLine();
                                    System.out.println("Enter surname");
                                    String nurseSurname = inn.nextLine();
                                    do {
                                        System.out.println("Enter EGN (must be 10 symbols long)");
                                        nurseEGN = inn.nextLine();
                                    } while (nurseEGN.length() != 10);
                                    do {
                                        System.out.println("Enter phone number (must be 10 symbols long)");
                                        nursePersonalPhoneNumber = inn.nextLine();
                                    } while (nursePersonalPhoneNumber.length() != 10);
                                    System.out.println("Enter address");
                                    String nurseAddress = inn.nextLine();
                                    long nursePhoneNumber = Long.parseLong(nursePersonalPhoneNumber);
                                    long nurseEgn = Long.parseLong(nurseEGN);
                                        addInfoToDB.addNursesToDB(nurseForname, nurseFathername, nurseSurname, nurseEgn, nurseAddress, nursePhoneNumber);
                                    break;
                                case 2:
                                    String doctorEGN;
                                    String doctorPersonalPhoneNumber;
                                    System.out.println("Enter forname");
                                    inn.nextLine();
                                    String doctorForname = inn.nextLine();
                                    System.out.println("Enter fathername");
                                    String doctorFathername = inn.nextLine();
                                    System.out.println("Enter surname");
                                    String doctorSurname = inn.nextLine();
                                    do {
                                        System.out.println("Enter EGN (must be 10 symbols long)");
                                        doctorEGN = inn.nextLine();
                                    } while (doctorEGN.length() != 10);
                                    do {
                                        System.out.println("Enter phone number (must be 10 symbols long)");
                                        doctorPersonalPhoneNumber = inn.nextLine();
                                    } while (doctorPersonalPhoneNumber.length() != 10);
                                    System.out.println("Enter address");
                                    String doctorAddress = inn.nextLine();
                                    long doctorPhoneNumber = Long.parseLong(doctorPersonalPhoneNumber);
                                    long doctorEgn = Long.parseLong(doctorEGN);
                                        addInfoToDB.addDoctorsToDB(doctorForname, doctorFathername, doctorSurname, doctorEgn, doctorAddress, doctorPhoneNumber);
                                    break;
                                case 3:
                                    String releaseNurseEGN;
                                    System.out.println("Enter nurse's EGN");
                                    do{
                                        releaseNurseEGN = inn.nextLine();
                                    }while (releaseNurseEGN.length() != 10);
                                    long releaseNurse = Long.parseLong(releaseNurseEGN);
                                        removeStaffFromDB.releaseNurse(releaseNurse);
                                    break;
                                case 4:
                                    String doctorReleaseEGN;
                                    System.out.println("Enter doctor's EGN");
                                    do{
                                        doctorReleaseEGN = inn.nextLine();
                                    }while (doctorReleaseEGN.length() != 10);
                                    long releaseDoctorEgn = Long.parseLong(doctorReleaseEGN);
                                        removeStaffFromDB.releaseDoctor(releaseDoctorEgn);
                                    break;
                                case 5:
                                    String checkDoctorEGN;
                                    do{
                                        System.out.println("Enter doctor's EGN");
                                        checkDoctorEGN = inn.nextLine();
                                    }while (checkDoctorEGN.length() != 10);
                                        getInformationFromDB.checkDoctorsInfo(checkDoctorEGN);
                                    break;
                                case 6:
                                    String nurseInfoEGN;
                                    System.out.println("Enter nurse's EGN: ");
                                    do{
                                        nurseInfoEGN = inn.nextLine();
                                    }while (nurseInfoEGN.length() != 10);
                                        getInformationFromDB.checkNursesInfo(nurseInfoEGN);
                                    break;
                                case 7:
                                        getInformationFromDB.checkPatientsInfo();
                                    break;
                                case 8:
                                    depHead = false;
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                    break;
                case 2:
                    isTrue = false;
                    System.exit(0);
                    inn.close();
                default:
                    break;
            }
        }
    }
}
