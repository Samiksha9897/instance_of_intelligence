package server_sharenow;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login implements Serializable {

    //Instance variable having usual names
    private String username,password;

    //public constructor for initializing instance variable
    public Login(LoginRequest user){
        this.username=user.getUsername();
        this.password=user.getPassword();
    }

    // method for validating user
    public User check(){
        String query="SELECT * from user_registration where Email='"+username+"';";
        User user= new User();;
        try {
            PreparedStatement stmt=Main.connection.prepareStatement(query);
            ResultSet rs=stmt.executeQuery();
            while (rs.next()){
                System.out.println(rs.getString(6));
                System.out.println((this.password));

                if((this.password).equals(rs.getString(6))){
                    user.setFullName(rs.getString(1));
                    user.setUsername(rs.getString(2));
                    user.setEmail(rs.getString(3));
                    user.setContact(rs.getString(4));
                    user.setVerificationStatus(String.valueOf(LoginStatus.VERIFIED));
                    return user;
                }
                user.setVerificationStatus(String.valueOf(LoginStatus.INCORRECT_PASSWORD));
                return user;//returned the string failed
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e);
            user.setVerificationStatus(String.valueOf(LoginStatus.DOES_NOT_EXIST));
            return user;
        }
        user = new User();
        user.setVerificationStatus(String.valueOf(LoginStatus.OTHER));
        return user;//returned error while entering data in database
    }

}
