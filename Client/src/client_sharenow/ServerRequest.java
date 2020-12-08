package client_sharenow;

public enum ServerRequest {
    ADMIN_LOGIN_REQUEST("0"), LOGIN_REQUEST("1"),
    SIGNUP_REQUEST("2"), USER_FETCH_REQUEST("3");


    ServerRequest(String s) {
        this.toString();
    }
}
