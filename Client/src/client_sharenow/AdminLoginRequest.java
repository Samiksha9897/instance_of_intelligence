package client_sharenow;

import java.io.Serializable;

public class AdminLoginRequest implements Serializable {

    private String adminName,adminpass;

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getAdminpass() {
        return adminpass;
    }

    public void setAdminpass(String adminpass) {
        this.adminpass = adminpass;
    }

    public AdminLoginRequest(String adminName , String adminnpass){
        this.adminName=adminName;
        this.adminpass=adminnpass;
    }

    @Override
    public String toString(){
        return String.valueOf(ServerRequest.ADMIN_LOGIN_REQUEST);
    }
}


