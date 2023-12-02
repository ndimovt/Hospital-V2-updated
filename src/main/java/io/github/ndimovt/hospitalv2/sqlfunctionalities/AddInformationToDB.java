package io.github.ndimovt.hospitalv2.sqlfunctionalities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AddInformationToDB {
    private Connection connection;
    private PreparedStatement preparedStatement;
   private static AddInformationToDB addInformationToDB;
    private AddInformationToDB() {
    }
    public static AddInformationToDB getInstance(){
        if(addInformationToDB == null){
            addInformationToDB = new AddInformationToDB();
        }
        return addInformationToDB;
    }
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
    }
    public void addDoctorsToDB(String forname, String fatherName, String surname, long egn, String address, long phoneNumber){
        try {
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.addDoctor(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, forname);
            preparedStatement.setString(2, fatherName);
            preparedStatement.setString(3, surname);
            preparedStatement.setLong(4, egn);
            preparedStatement.setString(5, address);
            preparedStatement.setLong(6, phoneNumber);
            preparedStatement.setString(7, DateAndTime.dateTime);
            preparedStatement.executeUpdate();
            System.out.println("Doctor added to database");
        } catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }
    public void addNursesToDB(String forname, String fatherName, String surname, long egn, String address, long phoneNumber) {
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.addNurse(?,?,?,?,?,?,?)");
            preparedStatement.setString(1, forname);
            preparedStatement.setString(2, fatherName);
            preparedStatement.setString(3, surname);
            preparedStatement.setLong(4, egn);
            preparedStatement.setString(5, address);
            preparedStatement.setLong(6, phoneNumber);
            preparedStatement.setString(7, DateAndTime.dateTime);
            preparedStatement.executeUpdate();
            System.out.println("Nurse added to database");
        }catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }
    public void addPatientToDB(String forname, String fatherName, String surname, long egn, String address){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.addPatient(?,?,?,?,?,?)");
            preparedStatement.setString(1,forname);
            preparedStatement.setString(2,fatherName);
            preparedStatement.setString(3,surname);
            preparedStatement.setLong(4, egn);
            preparedStatement.setString(5,address);
            preparedStatement.setString(6, DateAndTime.dateTime);
            preparedStatement.executeUpdate();
            System.out.println("Patient successfully added to DataBase");
        }catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        } finally {
            close(connection, preparedStatement);
        }
    }
    public void updatePatient(String illness, String treatment, long patientEGN){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.updatePatient(?,?,?)");
            preparedStatement.setString(1,illness);
            preparedStatement.setString(2,treatment);
            preparedStatement.setLong(3,patientEGN);
            preparedStatement.executeUpdate();
            System.out.println("Patient's info successfully updated!");
        } catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        }finally {
            close(connection, preparedStatement);
        }
    }
    private void close(Connection connection, PreparedStatement preparedStatement){
        try{
            if(connection != null){
                connection.close();
            }
            if(preparedStatement != null){
                preparedStatement.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
