package server_sharenow;

import java.io.Serializable;

public class SignupRequest extends User implements Serializable {

    private String password;
    public SignupRequest(String password) {
        this.password=password;
    }

    public String getPassword() {
        return password;
    }

    //method for returning request type
    @Override
    public String toString() {
        return String.valueOf(ServerRequest.SIGNUP_REQUEST);
    }

}
