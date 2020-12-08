package client_sharenow;

//importing appropriate classes

import java.io.Serializable;



/**
 * Class for the request
 * send by the client for
 * login of admin
 */
public class AdminLoginRequest implements Serializable {

    //Local variable of admin' request UserName and Password
    private String adminName,adminpass;

    /**
     * Getting the admin's username of Login Request send by client
     * @return Admin name of client of Login Request
     */
    public String getAdminName() {
        return adminName;
    }

    /**
     * Setting username of admin of Login Request
     * @param adminName Username of admin of Login Request
     */
    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    /**
     * Getting the admin's password of Login Request send by client
     * @return Password of admin of Login Request
     */
    public String getAdminpass() {
        return adminpass;
    }

    /**
     * Setting password of admin of Login Request send by client
     * @param adminpass Password of admin of Login Request
     */
    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass;
    }

    /**
     * Constructor of setting Username and Password of Login Request
     * @param adminName UserName of Admin
     * @param adminnpass Password of Admin
     */
    public AdminLoginRequest(String adminName , String adminnpass){
        this.adminName=adminName;
        this.adminpass=adminnpass;
    }

    /**
     * Method to return Type of Request
     * @return Appropriate String of request type
     */
    @Override
    public String toString(){
        return String.valueOf(ServerRequest.ADMIN_LOGIN_REQUEST);
    }
}

