package client_sharenow;

public enum LoginStatus {
    VERIFIED("0"),INCORRECT_PASSWORD("1"),OTHER("2"),FAILED_FROM_CLIENT("3"),DOES_NOT_EXIST("4");

    LoginStatus(String s){
        this.toString();
    }
}

