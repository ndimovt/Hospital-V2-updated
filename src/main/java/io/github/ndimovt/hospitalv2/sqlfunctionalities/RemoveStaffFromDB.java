package io.github.ndimovt.hospitalv2.sqlfunctionalities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class RemoveStaffFromDB {
    private Connection con;
    private PreparedStatement prst;
    private static RemoveStaffFromDB removeStaffFromDB;
    private RemoveStaffFromDB() {
    }
    public static RemoveStaffFromDB getInstance(){
        if(removeStaffFromDB == null){
            removeStaffFromDB = new RemoveStaffFromDB();
        }
        return removeStaffFromDB;
    }
    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/hospital", "root", "sheeuser123456@");
    }
        public void releasePatient(long egn){
        try{
            con  = this.getConnection();
            prst = con.prepareStatement("call hospital.removePatient(?,?)");
            prst.setString(1, DateAndTime.dateTime);
            prst.setLong(2,egn);
            prst.executeUpdate();
            System.out.println("Patient successfully released");
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        }
        finally {
            closeStreams(con, prst);
        }
    }
    public void releaseDoctor(long egn){
        try{
            con  = this.getConnection();
            prst = con.prepareStatement("call hospital.removeDoctor(?,?)");
            prst.setString(1, DateAndTime.dateTime);
            prst.setLong(2,egn);
            prst.executeUpdate();
            System.out.println("Operation is successful");
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        }
        finally {
            closeStreams(con, prst);
        }
    }
    public void releaseNurse(long egn){
        try{
            con  = this.getConnection();
            prst = con.prepareStatement("call hospital.removeNurse(?,?)");
            prst.setString(1, DateAndTime.dateTime);
            prst.setLong(2,egn);
            prst.executeUpdate();
            System.out.println("Operation is successful");
        }catch (SQLException sqe){
            System.out.println("No connection to database! Please try again later or call your IT support!");
            sqe.printStackTrace();
        }
        finally {
            closeStreams(con, prst);
        }
    }
    private void closeStreams(Connection connection, PreparedStatement pst){
        try{
            if(connection != null){
                connection.close();
            }
            if(pst != null){
                pst.close();
            }
        }catch (SQLException se){
            se.printStackTrace();
        }

    }
}
