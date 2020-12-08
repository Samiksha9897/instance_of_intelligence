package client_sharenow;

public enum ServerRequest {
    LOGIN_REQUEST("0"),SIGNUP_REQUEST("1"),ADMIN_LOGIN_REQUEST("2"),USER_FETCH_REQUEST("3");



    ServerRequest(String s) {
        this.toString();
    }
}