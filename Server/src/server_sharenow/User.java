package server_sharenow;

import java.io.Serializable;

public class User implements Serializable {
    protected String fullname,email,username,contact;
    private String verificationStatus;
private SERVER s=null;

    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

    public User(){

    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public String getFullName() {
        return fullname;

    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getContact() {
        return contact;
    }

    @Override
    public String toString(){
        return this.getFullName();
    }



}
