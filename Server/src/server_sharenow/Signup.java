package server_sharenow;

//import database connection library
import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static server_sharenow.SignupStatus.*;
import static server_sharenow.SignupStatus.*;

public class Signup implements Serializable {
    //Instance variable
    //names
    private String email,fullname,username,contact,password;
    private String query;

    //public constructor
    public Signup(SignupRequest user){
        this.email=user.getEmail();
        this.contact=user.getContact();
        this.fullname=user.getFullName();
        this.username=user.getUsername();
        this.password=user.getPassword();
    }
    //method to add instance variable values to database
    public String put(){
        //String query1="INSERT INTO AmountSpendUser(UserID) VALUES (?)";
        if(this.contact==null){
            this.query="INSERT INTO user_registration(full_name,username,password,email_id,contact) VALUES (?,?,?,?,?);";
        }
        else {
            this.query="INSERT INTO user_registration VALUES( ? , ? , ? , ? , ? , ? ,0);";
        }
        try {
            PreparedStatement stmt=SocketServer.connection.prepareStatement(this.query);
            //PreparedStatement preparedStatement=Main.connection.prepareStatement(query1);
            stmt.setString(1,this.fullname);
            stmt.setString(2,this.username);
            stmt.setString(3,this.email);
            if (this.contact==null){
                stmt.setString(4,this.password);
            }
            else {
                stmt.setString(4,this.contact);
                stmt.setString(5,this.password);
            }
            stmt.executeUpdate();
            //preparedStatement.executeUpdate();
            return String.valueOf(SignupStatus.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return String.valueOf(SignupStatus.FAILED);
        }
    }
}