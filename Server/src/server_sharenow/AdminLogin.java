package server_sharenow;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Class for checking admin
 * username and password and
 * returning appropriate message
 */
public class AdminLogin {

    //Object variable current request by client
    private AdminLoginRequest adminLoginRequest;

    /**
     * Initializing the local variable of adminLoginRequest
     * @param adminLoginRequest Object of the request send by client
     */
    public AdminLogin(AdminLoginRequest adminLoginRequest){
        this.adminLoginRequest=adminLoginRequest;
    }

    /**
     * Method to check Admin
     * Password and Username
     * @return Appropriate String of type AdminLoginStatus enum
     */
    public String check(){
        if(adminLoginRequest.getAdminName().equals(Main.adminuser) && adminLoginRequest.getAdminpass().equals(Main.adminpass)){//checking admin Credential
            return String.valueOf(AdminLoginStatus.SUCCESS);
        }
        else return String.valueOf(AdminLoginStatus.FAILED);
    }
}
