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
            preparedStatement = connection.prepareStatement("""
                    SELECT p.forename, p.fathername, p.surname, p.EGN, ppd.adress, ppd.illness, ppd.treatment, ppd.date_in
                    FROM patients p
                    JOIN patients_personal_data ppd ON p.EGN = ppd.EGN
                    """);
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
                System.out.println(patient);
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
            preparedStatement = connection.prepareStatement("""
                    SELECT d.forename, d.fathername, d.surname, dpd.address, dpd.phone_number, dpd.date_in
                    FROM doctors d
                    JOIN doctors_personal_data dpd ON d.EGN = dpd.EGN
                    WHERE d.EGN = ?
                    """);
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
            preparedStatement = connection.prepareStatement("""
                    SELECT n.forename, n.fathername, n.surname, npd.address, npd.phone_number, npd.date_in
                    FROM nurses n
                    JOIN nurses_personal_data npd ON n.EGN = npd.EGN
                    WHERE n.EGN = ?
                    """
            );
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
