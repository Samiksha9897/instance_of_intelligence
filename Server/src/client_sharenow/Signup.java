package client_sharenow;

//import database connection library

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.SQLException;

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
         this.query = "INSERT INTO user_registration(full_name,username,password,email_id,contact) VALUES(?,?,?,?,?);";

        try {
            PreparedStatement stmt= Main.connection.prepareStatement(query);
            stmt.setString(1,this.fullname);
            stmt.setString(2,this.username);
            stmt.setString(3,this.password);
            stmt.setString(4,this.email);
            stmt.setString(5,this.contact);
            System.out.println(this.fullname);
            System.out.println(this.email);
            System.out.println(this.contact);
            System.out.println(this.username);
            stmt.executeUpdate();
            return String.valueOf(SignupStatus.SUCCESS);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            return String.valueOf(SignupStatus.FAILED);
        }
    }
}
