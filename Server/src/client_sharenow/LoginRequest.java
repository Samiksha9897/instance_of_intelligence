package client_sharenow;

import java.io.Serializable;

public class LoginRequest implements Serializable {
    private static final long serialVersionUID = 7304267833309396813L;

    private String username,password;

    public LoginRequest(String username,String password) {
        this.username=username;
        this.password=password;
    }

    public String getUsername() {
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
