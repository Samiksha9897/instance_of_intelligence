package client_sharenow;

import java.io.Serializable;

public class LoginRequest implements Serializable {

    private String username,password;

    public LoginRequest(String username, String password) {
        this.username= username;
        this.password=password;
    }

    public String getEmail() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return String.valueOf(ServerRequest.LOGIN_REQUEST);
    }
}
