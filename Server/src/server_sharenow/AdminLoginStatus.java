package server_sharenow;

/**
 *Status of the Admin Login Request
 */
public enum AdminLoginStatus {
    /**
     *Successful Login
     */
    SUCCESS("0"),
    /**
     * Login Failed
     */
    FAILED("1");

    /**
     *
     * @param s String value of the enum
     */
    AdminLoginStatus(String s){
        s.toString();
    }
}