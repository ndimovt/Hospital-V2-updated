package io.github.ndimovt.hospitalv2.sqlfunctionalities;

import io.github.ndimovt.hospitalv2.Doctor;
import io.github.ndimovt.hospitalv2.Human;
import io.github.ndimovt.hospitalv2.Nurse;
import io.github.ndimovt.hospitalv2.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;
public class GetInformationFromDB {
    private Connection connection;
    private ResultSet resultSet;
    private PreparedStatement preparedStatement;
    private static GetInformationFromDB getInformationFromDB;
    private GetInformationFromDB() {
    }
    public static GetInformationFromDB getInstance(){
        if(getInformationFromDB == null){
            getInformationFromDB = new GetInformationFromDB();
        }
        return getInformationFromDB;
    }
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
    }
    public void checkPatientsInfo(){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.noTreatment()");
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Human patient = new Patient(
                        resultSet.getString("forename"),
                        resultSet.getString("fathername"),
                        resultSet.getString("surname"),
                        resultSet.getLong("EGN"),
                        resultSet.getString("adress"),
                        resultSet.getString("illness"),
                        resultSet.getString("treatment"),
                        resultSet.getString("date_in"));
                System.out.println(patient.patientToString());
            }
        }catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
    public void checkDoctorsInfo(String doctorInfoEGN){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.getDoctor(?)");
            preparedStatement.setString(1,doctorInfoEGN);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Human doctor = new Doctor(resultSet.getString("forename"),
                        resultSet.getString("fathername"),
                        resultSet.getString("surname"),
                        resultSet.getString("address"),
                        resultSet.getLong("phone_number"),
                        resultSet.getString("date_in"));
                System.out.println(doctor);
            }
        }catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        } finally {
            close(connection, preparedStatement, resultSet);
        }
    }
    public void checkNursesInfo(String nurseEGN){
        try{
            connection = this.getConnection();
            preparedStatement = connection.prepareStatement("call hospital.getNurse(?)");
            preparedStatement.setString(1,nurseEGN);
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Human nurse = new Nurse(resultSet.getString("forename"),
                        resultSet.getString("fathername"),
                        resultSet.getString("surname"),
                        resultSet.getString("address"),
                        resultSet.getLong("phone_number"),
                        resultSet.getString("date_in"));
                System.out.println(nurse);
            }
        }catch (SQLException sqlException){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqlException.printStackTrace();
        }finally {
            close(connection, preparedStatement, resultSet);
        }
    }
    private void close(Connection con, PreparedStatement pst, ResultSet rs){
        try{
            if(con != null){
                con.close();
            }
            if(pst != null){
                pst.close();
            }
            if(rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
