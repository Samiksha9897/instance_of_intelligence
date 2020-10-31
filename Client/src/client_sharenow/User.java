package client_sharenow;

import java.io.Serializable;

public class User implements Serializable {
    protected String fullname,userName,email,contact;
    private String verificationStatus;


    public void setFullName(String fullname) {
        this.fullname = fullname;
    }

    public void setLastName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String contact) {
        this.contact = contact;
    }

    /*public void setId(String id) {
        this.id = id;
    }*/


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

    public String getLastName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public String getContact() {
        return contact;
    }


    @Override
    public String toString(){
        return this.getFullName();
    }
}
